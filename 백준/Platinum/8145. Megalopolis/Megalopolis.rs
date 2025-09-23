use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    n: usize,
    tree: Vec<u32>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tree: vec![0; n * 2] }
    }
    fn build(&mut self, base: &[u32]) {
        for i in 0..base.len() { self.tree[self.n + i] = base[i]; }
        for i in (1..self.n).rev() { self.tree[i] = self.tree[i << 1] + self.tree[i << 1 | 1]; }
    }
    fn update(&mut self, mut idx: usize, val: u32) {
        idx += self.n;
        self.tree[idx] = val;
        idx >>= 1;
        while idx > 0 {
            self.tree[idx] = self.tree[idx << 1] + self.tree[idx << 1 | 1];
            idx >>= 1;
        }
    }
    fn query(&self, mut l: usize, mut r: usize) -> u32 {
        l += self.n; r += self.n;
        let mut res = 0u32;
        while l < r {
            if (l & 1) == 1 { res += self.tree[l]; l += 1; }
            if (r & 1) == 1 { r -= 1; res += self.tree[r]; }
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

fn country_roads(mut a: usize, mut b: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize], seg: &SegTree) -> u32 {
    let mut res = 0u32;
    while head[a] != head[b] {
        if depth[head[a]] < depth[head[b]] { std::mem::swap(&mut a, &mut b); }
        let ha = head[a];
        res += seg.query(pos[ha], pos[a] + 1);
        a = parent[ha];
    }
    if depth[a] > depth[b] { std::mem::swap(&mut a, &mut b); }
    if a != b {
        res += seg.query(pos[a] + 1, pos[b] + 1);
    }
    res
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut g = vec![Vec::<usize>::new(); n + 1];
    for _ in 1..n {
        let a = next!(it, usize);
        let b = next!(it, usize);
        g[a].push(b);
        g[b].push(a);
    }

    let (mut parent, mut depth, mut size, mut heavy) = 
        (vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let (mut head, mut pos) = (vec![0usize; n + 1], vec![0usize; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut cur);

    let mut base = vec![0u32; n];
    for x in 2..=n { base[pos[x]] = 1; }
    let mut seg = SegTree::new(n);
    seg.build(&base);

    let m = next!(it, usize);
    let mut out = String::new();
    for _ in 1..(n + m) {
        let q = it.next().unwrap();
        match q {
            "W" => {
                let a = next!(it, usize);
                let ans = country_roads(1, a, &head, &depth, &parent, &pos, &seg);
                out.push_str(&format!("{ans}\n"));
            }
            "A" => {
                let a = next!(it, usize);
                let b = next!(it, usize);
                let child = if parent[a] == b { a } else { b };
                seg.update(pos[child], 0);

            }
            _ => unreachable!(),
        }
    }
    print!("{out}");
}