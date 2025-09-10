use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone, Copy)]
struct Adj { to: usize, id: usize }

struct SegTree {
    n: usize,
    tree: Vec<i64>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tree: vec![i64::MIN; n * 2] }
    }
    fn build(&mut self, base: &[i64]) {
        let m = self.n;
        for i in 0..base.len() { self.tree[m + i] = base[i]; }
        for i in (1..m).rev() {
            self.tree[i] = self.tree[i << 1].max(self.tree[i << 1 | 1]);
        }
    }
    fn update(&mut self, mut idx: usize, val: i64) {
        idx += self.n;
        self.tree[idx] = val;
        idx >>= 1;
        while idx > 0 {
            self.tree[idx] = self.tree[idx << 1].max(self.tree[idx << 1 | 1]);
            idx >>= 1;
        }
    }
    fn query(&self, mut l: usize, mut r: usize) -> i64 {
        let m = self.n;
        l += m; r += m;
        let mut res = i64::MIN;
        while l < r {
            if (l & 1) == 1 { res = res.max(self.tree[l]); l += 1; }
            if (r & 1) == 1 { r -= 1; res = res.max(self.tree[r]); }
            l >>= 1; r >>= 1;
        }
        res
    }
}

fn dfs1(u: usize, p: usize, g: &Vec<Vec<Adj>>,
        parent: &mut [usize], depth: &mut [usize], size: &mut [usize], heavy: &mut [usize]) {
    parent[u] = p;
    depth[u] = if p == 0 { 0 } else { depth[p] + 1 };
    size[u] = 1;
    let mut best = 0usize;
    for &e in &g[u] {
        if e.to == p { continue; }
        dfs1(e.to, u, g, parent, depth, size, heavy);
        size[u] += size[e.to];
        if size[e.to] > best { best = size[e.to]; heavy[u] = e.to; }
    }
}

fn dfs2(u: usize, h: usize, g: &Vec<Vec<Adj>>,
    parent: &[usize], heavy: &[usize],
    head: &mut [usize], pos: &mut [usize], cur: &mut usize) {
    head[u] = h;
    pos[u] = *cur;
    *cur += 1;

    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, cur);
    }
    for &e in &g[u] {
        if e.to == parent[u] || e.to == heavy[u] { continue; }
        dfs2(e.to, e.to, g, parent, heavy, head, pos, cur);
    }
}

fn query_path_max(mut a: usize, mut b: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize], seg: &SegTree) -> i64 {
    let mut res = i64::MIN;
    while head[a] != head[b] {
        if depth[head[a]] < depth[head[b]] { std::mem::swap(&mut a, &mut b); }
        let ha = head[a];
        res = res.max(seg.query(pos[ha], pos[a] + 1));
        a = parent[ha];
    }
    if depth[a] > depth[b] { std::mem::swap(&mut a, &mut b); }
    if a != b {
        res = res.max(seg.query(pos[a] + 1, pos[b] + 1));
    } else {
        res = res.max(0);
    }
    res
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);

    let mut u = vec![0usize; n];
    let mut v = vec![0usize; n];
    let mut w = vec![0i64; n];
    let mut g = vec![Vec::<Adj>::new(); n + 1];

    for i in 1..n {
        u[i] = next!(it, usize);
        v[i] = next!(it, usize);
        w[i] = next!(it, i64);
        g[u[i]].push(Adj { to: v[i], id: i });
        g[v[i]].push(Adj { to: u[i], id: i });
    }

    let mut parent = vec![0usize; n + 1];
    let mut depth = vec![0usize; n + 1];
    let mut size = vec![0usize; n + 1];
    let mut heavy = vec![0usize; n + 1];

    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let mut head = vec![0usize; n + 1];
    let mut pos = vec![0usize; n + 1];
    let mut cur = 0usize;

    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut cur);

    let mut base = vec![0i64; n];
    let mut edge_to_node = vec![0usize; n];
    for i in 1..n {
        let deeper = if depth[u[i]] > depth[v[i]] { u[i] } else { v[i] };
        edge_to_node[i] = deeper;
        base[pos[deeper]] = w[i];
    }

    let mut seg = SegTree::new(n);
    seg.build(&base);
    
    let q = next!(it, usize);
    let mut out = String::new();
    
    for _ in 0..q {
        let t = next!(it, usize);
        if t == 1 {
            let e_idx = next!(it, usize);
            let val = next!(it, i64);
            w[e_idx] = val;
            let node = edge_to_node[e_idx];
            seg.update(pos[node], val);
        } else {
            let a = next!(it, usize);
            let b = next!(it, usize);
            let answer = query_path_max(a, b, &head, &depth, &parent, &pos, &seg);
            out.push_str(&format!("{answer}\n"));
        }
    }

    print!("{out}");
}