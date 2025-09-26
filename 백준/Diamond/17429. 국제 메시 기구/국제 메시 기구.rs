use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

const MASK: u64 = 0xFFFF_FFFF;

struct SegTree {
    n: usize,
    tree: Vec<u64>,
    la: Vec<u64>,
    lb: Vec<u64>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tree: vec![0; n * 2], la: vec![1; n * 2], lb: vec![0; n * 2] }
    }
    #[inline]
    fn update_lazy(&mut self, i: usize, a: u64, b: u64, len: usize) {
        let add = (b * (len as u64)) & MASK;
        self.tree[i] = (((a * self.tree[i]) & MASK) + add) & MASK;
        self.la[i] = (a * self.la[i]) & MASK;
        self.lb[i] = (((a * self.lb[i]) & MASK) + b) & MASK;
    }
    #[inline]
    fn push(&mut self, i: usize, len: usize) {
        if self.la[i] != 1 || self.lb[i] != 0 {
            let a = self.la[i]; let b = self.lb[i];
            let l_len = len >> 1; let r_len = len - l_len;
            self.update_lazy(i << 1, a, b, l_len);
            self.update_lazy(i << 1 | 1, a, b, r_len);
            self.la[i] = 1; self.lb[i] = 0;
        }
    }
    fn range_update(&mut self, l: usize, r: usize, a: u64, b: u64) {
        self._update(1, 0, self.n, l, r, a, b);
    }
    fn _update(&mut self, i: usize, s: usize, e: usize, l: usize, r: usize, a: u64, b: u64) {
        if r <= s || e <= l { return; }
        if l <= s && e <= r { self.update_lazy(i, a, b, e - s); return; }
        self.push(i, e - s);
        let m = (s + e) / 2;
        self._update(i << 1, s, m, l, r, a, b);
        self._update(i << 1 | 1, m, e, l, r, a, b);
        self.tree[i] = (self.tree[i << 1] + self.tree[i << 1 | 1]) & MASK;
    }
    fn range_query(&mut self, l: usize, r: usize) -> u64 {
        self._query(1, 0, self.n, l, r)
    }
    fn _query(&mut self, i: usize, s: usize, e: usize, l: usize, r: usize) -> u64 {
        if r <= s || e <= l { return 0; }
        if l <= s && e <= r { return self.tree[i]; }
        self.push(i, e - s);
        let m = (s + e) / 2;
        let a = self._query(i << 1, s, m, l, r);
        let b = self._query(i << 1 | 1, m, e, l, r);
        (a + b) & MASK
    }
}

struct HLD {
    g: Vec<Vec<usize>>,
    parent: Vec<usize>,
    depth: Vec<usize>,
    size: Vec<usize>,
    heavy: Vec<usize>,
    head: Vec<usize>,
    pos: Vec<usize>,
    cur: usize,
}
impl HLD {
    fn new(n: usize) -> Self {
        Self {
            g: vec![Vec::new(); n + 1],
            parent: vec![0; n + 1],
            depth: vec![0; n + 1],
            size: vec![0; n + 1],
            heavy: vec![0; n + 1],
            head: vec![0; n + 1],
            pos: vec![0; n + 1],
            cur: 0,
        }
    }
    fn add_edge(&mut self, a: usize, b: usize) { 
        self.g[a].push(b); self.g[b].push(a);
    }
    fn build(&mut self, root: usize) {
        self.dfs1(root, 0);
        self.dfs2(root, root);
    }
    fn dfs1(&mut self, u: usize, p: usize) {
        self.parent[u] = p;
        self.depth[u] = if p == 0 { 0 } else { self.depth[p] + 1 };
        self.size[u] = 1;
        let neighbors = self.g[u].clone();
        let mut best = 0usize;
        for v in neighbors {
            if v == p { continue; }
            self.dfs1(v, u);
            self.size[u] += self.size[v];
            if self.size[v] > best { best = self.size[v]; self.heavy[u] = v; }
        }
    }
    fn dfs2(&mut self, u: usize, h: usize) {
        self.head[u] = h;
        self.pos[u] = self.cur; self.cur += 1;
        if self.heavy[u] != 0 {
            self.dfs2(self.heavy[u], h);
        }
        let neighbors = self.g[u].clone();
        for v in neighbors {
            if v == self.parent[u] || v == self.heavy[u] { continue; }
            self.dfs2(v, v);
        }
    }
    fn get_subtree(&self, x: usize) -> (usize, usize) {
        (self.pos[x], self.pos[x] + self.size[x])
    }
    fn path_closure<F: FnMut(usize, usize)>(&self, mut u: usize, mut v: usize, mut f: F) {
        while self.head[u] != self.head[v] {
            if self.depth[self.head[u]] < self.depth[self.head[v]] { std::mem::swap(&mut u, &mut v); }
            let hu = self.head[u];
            f(self.pos[hu], self.pos[u] + 1);
            u = self.parent[hu];
        }
        if self.depth[u] > self.depth[v] { std::mem::swap(&mut u, &mut v); }
        f(self.pos[u], self.pos[v] + 1);
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let q = next!(it, usize);

    let mut hld = HLD::new(n);
    for _ in 1..n {
        let a = next!(it, usize);
        let b = next!(it, usize);
        hld.add_edge(a, b);
    }
    hld.build(1);

    let mut seg = SegTree::new(n);
    let mut out = String::new();
    
    for _ in 0..q {
        let t = next!(it, usize);
        match t {
            1 => {
                let x = next!(it, usize);
                let v = next!(it, u64) & MASK;
                let (l, r) = hld.get_subtree(x);
                seg.range_update(l, r, 1, v);
            }
            2 => {
                let x = next!(it, usize);
                let y = next!(it, usize);
                let v = next!(it, u64) & MASK;
                hld.path_closure(x, y, |l, r| seg.range_update(l, r, 1, v));
            }
            3 => {
                let x = next!(it, usize);
                let v = next!(it, u64) & MASK;
                let (l, r) = hld.get_subtree(x);
                seg.range_update(l, r, v, 0);
            }
            4 => {
                let x = next!(it, usize);
                let y = next!(it, usize);
                let v = next!(it, u64) & MASK;
                hld.path_closure(x, y, |l, r| seg.range_update(l, r, v, 0));
            }
            5 => {
                let x = next!(it, usize);
                let (l, r) = hld.get_subtree(x);
                let ans = seg.range_query(l, r) & MASK;
                out.push_str(&format!("{}\n", ans));
            }
            6 => {
                let x = next!(it, usize);
                let y = next!(it, usize);
                let mut ans = 0u64;
                hld.path_closure(x, y, |l, r| { ans = (ans + seg.range_query(l, r)) & MASK; });
                out.push_str(&format!("{}\n", ans));
            }
            _ => unreachable!(),
        }
    }
    print!("{out}");
}