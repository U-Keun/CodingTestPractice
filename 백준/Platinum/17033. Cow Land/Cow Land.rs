use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    n: usize,
    tree: Vec<u64>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tree: vec![0; n * 2] }
    }
    fn build(&mut self, base: &[u64]) {
        for i in 0..base.len() { self.tree[self.n + i] = base[i]; }
        for i in (1..self.n).rev() { self.tree[i] = self.tree[i << 1] ^ self.tree[i << 1 | 1]; }
    }
    fn update(&mut self, mut idx: usize, val: u64) {
        idx += self.n;
        self.tree[idx] = val; idx >>= 1;
        while idx > 0 {
            self.tree[idx] = self.tree[idx << 1] ^ self.tree[idx << 1 | 1];
            idx >>= 1;
        }
    }
    fn query(&self, mut l: usize, mut r: usize) -> u64 {
        let m = self.n; l += m; r += m;
        let mut res = 0u64;
        while l < r {
            if (l & 1) == 1 { res ^= self.tree[l]; l += 1; }
            if (r & 1) == 1 { r -= 1; res ^= self.tree[r]; }
            l >>= 1; r >>= 1;
        }
        res
    }
}

fn dfs1(u: usize, p: usize, g: &Vec<Vec<usize>>,
    parent: &mut [usize], depth: &mut [usize], size: &mut [usize], heavy: &mut [usize]) {
    parent[u] = p;
    depth[u] = if p == 0 { 0 } else { depth[p] + 1 };
    size[u] = 1;
    heavy[u] = 0;
    let mut best = 0usize;
    for &v in &g[u] {
        if v == p { continue; }
        dfs1(v, u, g, parent, depth, size, heavy);
        size[u] += size[v];
        if size[v] > best { best = size[v]; heavy[u] = v; }
    }
}

fn dfs2(u: usize, h: usize, g: &Vec<Vec<usize>>,
    parent: &[usize], heavy: &[usize],
    head: &mut [usize], pos: &mut [usize], rev: &mut [usize], cur: &mut usize) {
    head[u] = h;
    pos[u] = *cur; rev[*cur] = u; *cur += 1;
    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, rev, cur);
    }
    for &v in &g[u] {
        if v == parent[u] || v == heavy[u] { continue; }
        dfs2(v, v, g, parent, heavy, head, pos, rev, cur);
    }
}

fn path_xor(mut u: usize, mut v: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize], seg: &SegTree) -> u64 {
    let mut ans = 0u64;
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let hu = head[u];
        ans ^= seg.query(pos[hu], pos[u] + 1);
        u = parent[hu];
    }
    if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
    ans ^ seg.query(pos[u], pos[v] + 1)
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let q = next!(it, usize);

    let mut val = vec![0u64; n + 1];
    for i in 1..=n { val[i] = next!(it, u64); }
    
    let mut g = vec![Vec::<usize>::new(); n + 1];
    for _ in 1..n {
        let a = next!(it, usize);
        let b = next!(it, usize);
        g[a].push(b); g[b].push(a);
    }

    let (mut parent, mut depth, mut size, mut heavy) =
        (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let (mut head, mut pos, mut rev) = (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut rev, &mut cur);

    let mut base = vec![0u64; n];
    for u in 1..=n { base[pos[u]] = val[u]; }
    let mut seg = SegTree::new(n);
    seg.build(&base);

    let mut out = String::new();
    for _ in 0..q {
        let t = next!(it, usize);
        if t == 1 {
            let i = next!(it, usize);
            let v = next!(it, u64);
            seg.update(pos[i], v);
        } else {
            let a = next!(it, usize);
            let b = next!(it, usize);
            let ans = path_xor(a, b, &head, &depth, &parent, &pos, &seg);
            out.push_str(&format!("{ans}\n"));
        }
    }
    print!("{out}");
}