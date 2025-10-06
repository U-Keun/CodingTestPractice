use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    n: usize,
    min_tree: Vec<i64>,
    lazy: Vec<i64>,
}
impl SegTree {
    fn new(base: &[i64]) -> Self {
        let mut n = 1usize;
        let len = base.len();
        while n < len { n <<= 1; }
        let mut o = Self { n, min_tree: vec![0; n * 2], lazy: vec![0; n * 2] };
        for i in 0..len { o.min_tree[o.n + i] = base[i]; }
        for i in (1..o.n).rev() {
            o.min_tree[i] = o.min_tree[i << 1].min(o.min_tree[i << 1 | 1]);
        }
        o
    }
    #[inline]
    fn apply(&mut self, idx: usize, v: i64) {
        self.min_tree[idx] += v;
        if idx < self.n { self.lazy[idx] += v; }
    }
    #[inline]
    fn push(&mut self, idx: usize) {
        let v = self.lazy[idx];
        if v != 0 {
            self.apply(idx << 1, v);
            self.apply(idx << 1 | 1, v);
            self.lazy[idx] = 0;
        }
    }
    fn add_internal(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize, v: i64) {
        if r <= s || e <= l { return; }
        if l <= s && e <= r {
            self.apply(idx, v);
            return;
        }
        self.push(idx);
        let mid = (s + e) >> 1;
        self.add_internal(idx << 1, s, mid, l, r, v);
        self.add_internal(idx << 1 | 1, mid, e, l, r, v);
        self.min_tree[idx] = self.min_tree[idx << 1].min(self.min_tree[idx << 1 | 1]);
    }
    fn range_add(&mut self, l: usize, r: usize, v: i64) {
        self.add_internal(1, 0, self.n, l, r, v);
    }
    fn global_min(&self) -> i64 {
        self.min_tree[1]
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
        if size[v] > best {
            best = size[v];
            heavy[u] = v;
        }
        size[u] += size[v];
    }
}

fn dfs2(u: usize, h: usize, g: &Vec<Vec<usize>>,
    parent: &[usize], heavy: &[usize], head: &mut [usize], pos: &mut [usize], cur: &mut usize) {
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

fn path_add(mut u: usize, mut v: usize, w: i64, seg: &mut SegTree,
    head: &[usize], pos: &[usize], depth: &[usize], parent: &[usize]) -> bool {
    let mut exceeded = false;
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let hu = head[u];
        let (l, r) = (pos[hu], pos[u]);
        seg.range_add(l, r + 1, w);
        if seg.global_min() < 0 { exceeded = true; break; }
        u = parent[hu];
    }
    if !exceeded {
        if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
        seg.range_add(pos[u], pos[v] + 1, w);
        if seg.global_min() < 0 { exceeded = true; }
    }
    exceeded
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let (n, m) = (next!(it, usize), next!(it, usize));
    let mut g = vec![Vec::<usize>::new(); n + 1];
    for _ in 1..n {
        let (a, b) = (next!(it, usize), next!(it, usize));
        g[a].push(b);
        g[b].push(a);
    }

    let (mut parent, mut depth, mut size, mut heavy, mut head, mut pos) =
        (vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1], 
        vec![0usize; n + 1], vec![0usize; n + 1]);
    let mut cur = 0usize;

    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut cur);

    let mut lim = vec![0i64; n + 1];
    for i in 1..=n { lim[i] = next!(it, i64); }
    
    let mut base = vec![0i64; n];
    for j in 1..=n { base[pos[j]] = lim[j]; }
    let mut seg = SegTree::new(&base);

    let mut cnt = 0usize;
    for _ in 0..m {
        let (x, y, w) = (next!(it, usize), next!(it, usize), next!(it, i64));
        let exceeded = path_add(x, y, -w, &mut seg, &head, &pos, &depth, &parent);
        if exceeded {
            println!("{cnt}");
            return;
        }
        cnt += 1;
    }
    println!("{cnt}");
}