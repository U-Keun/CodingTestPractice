use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    n: usize,
    tree: Vec<u8>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tree: vec![0; n * 2] }
    }
    fn update(&mut self, mut idx: usize, val: u8) {
        idx += self.n;
        self.tree[idx] = val;
        idx >>= 1;
        while idx > 0 {
            self.tree[idx] = self.tree[idx << 1].max(self.tree[idx << 1 | 1]);
            idx >>= 1;
        }
    }
    fn query(&self, mut l: usize, mut r: usize) -> u8 {
        let m = self.n;
        l += m; r += m;
        let mut res = 0;
        while l < r {
            if (l & 1) == 1 { res = res.max(self.tree[l]); l += 1; }
            if (r & 1) == 1 { r -= 1; res = res.max(self.tree[r]); }
            l >>= 1; r >>= 1;
        }
        res
    }
}

fn dfs1(u: usize, p: usize, g: &Vec<Vec<usize>>,
    depth: &mut [usize], size: &mut [usize], heavy: &mut [usize]) {
    depth[u] = if p == 0 { 0 } else { depth[p] + 1 };
    size[u] = 1;
    let mut best = 0usize;
    for &v in &g[u] {
        if v == p { continue; }
        dfs1(v, u, g, depth, size, heavy);
        size[u] += size[v];
        if size[v] > best { best = size[v]; heavy[u] = v; }
    }
}

fn dfs2(u: usize, h: usize, g: &Vec<Vec<usize>>,
    parent: &[usize], heavy: &[usize],
    head: &mut [usize], pos: &mut [usize], cur: &mut usize) {
    head[u] = h;
    pos[u] = *cur;
    *cur += 1;

    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, cur);
    }
    for &v in &g[u] {
        if v == parent[u] || v == heavy[u] { continue; }
        dfs2(v, v, g, parent, heavy, head, pos, cur);
    }
}

fn path_is_open(mut u: usize, mut v: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize], seg: &SegTree) -> bool {
    let mut mx = 0u8;
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let hu = head[u];
        mx = mx.max(seg.query(pos[hu], pos[u] + 1));
        if mx == 1 { return false; }
        u = parent[hu];
    }
    if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
    if u != v {
        mx = mx.max(seg.query(pos[u] + 1, pos[v] + 1));
    }
    mx == 0
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let q = next!(it, usize);

    let mut parent = vec![0usize; n + 1];
    let mut g: Vec<Vec<usize>> = vec![Vec::new(); n + 1];    

    for i in 2..=n {
        let p = next!(it, usize);
        parent[i] = p;
        g[i].push(p);
        g[p].push(i);
    }

    let mut depth = vec![0usize; n + 1];
    let mut size = vec![0usize; n + 1];
    let mut heavy = vec![0usize; n + 1];

    dfs1(1, 0, &g, &mut depth, &mut size, &mut heavy);

    let mut head = vec![0usize; n + 1];
    let mut pos = vec![0usize; n + 1];
    let mut cur = 0usize;
    
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut cur);

    let mut seg = SegTree::new(n);

    let mut out = String::new();
    for _ in 0..q {
        let b = next!(it, usize);
        let c = next!(it, usize);
        let d = next!(it, usize);

        let is_open = path_is_open(b, c, &head, &depth, &parent, &pos, &seg);
        if is_open {
            out.push_str(&format!("YES\n"));
            if d == 1 { seg.update(pos[b], 1); }
        } else {
            out.push_str(&format!("NO\n"));
            if d == 1 { seg.update(pos[c], 1); }
        }
    }
    print!("{out}");
}