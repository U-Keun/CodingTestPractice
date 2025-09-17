use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct UnionFind { p: Vec<usize> }
impl UnionFind {
    fn new(n: usize) -> Self { 
        let mut p = Vec::with_capacity(n + 1); 
        for i in 0..=n {
            p.push(i);
        }
        Self { p }
    }
    fn find(&mut self, x: usize) -> usize {
        if self.p[x] == x { x }
        else {
            let r = self.find(self.p[x]);
            self.p[x] = r;
            r
        }
    }
    fn union(&mut self, a: usize, b: usize) -> bool {
        let ra = self.find(a);
        let rb = self.find(b);
        if ra == rb { 
            false 
        } else {
            self.p[ra] = rb;
            true
        }
    }
}

struct SegTree {
    n: usize, tree: Vec<i64>
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tree: vec![0; n * 2] }
    }
    fn build(&mut self, base: &[i64]) {
        for i in 0..base.len() {
            self.tree[self.n + i] = base[i];
        }
        for i in (1..self.n).rev() {
            self.tree[i] = self.tree[i << 1] + self.tree[i << 1 | 1];
        }
    }
    fn update(&mut self, mut idx: usize, val: i64) {
        idx += self.n;
        self.tree[idx] = val; idx >>= 1;
        while idx > 0 {
            self.tree[idx] = self.tree[idx << 1] + self.tree[idx << 1 | 1];
            idx >>= 1;
        }
    }
    fn query(&self, mut l: usize, mut r: usize) -> i64 {
        let m = self.n; l += m; r += m;
        let mut res = 0;
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
    size[u] = 1; heavy[u] = 0;
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
    pos[u] = *cur;
    rev[*cur] = u;
    *cur += 1;
    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, rev, cur);
    }
    for &v in &g[u] {
        if v == parent[u] || v == heavy[u] { continue; }
        dfs2(v, v, g, parent, heavy, head, pos, rev, cur);
    }
}

fn path_sum(mut u: usize, mut v: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize], seg: &SegTree) -> i64 {
    let mut ans = 0i64;
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let hu = head[u];
        ans += seg.query(pos[hu], pos[u] + 1);
        u = parent[hu];
    }
    
    if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
    ans += seg.query(pos[u], pos[v] + 1);
    ans
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut val = vec![0i64; n + 1];
    for i in 1..=n { val[i] = next!(it, i64); }

    let q = next!(it, usize);
    #[derive(Clone)]
    enum Op { Bridge(usize, usize), Penguins(usize, i64), Excursion(usize, usize) }
    let mut queries: Vec<Op> = Vec::with_capacity(q);

    let mut uf1 = UnionFind::new(n);
    let mut g = vec![Vec::new(); n + 1];
    for _ in 0..q {
        let op = it.next().unwrap();
        match op {
            "bridge" => {
                let a = next!(it, usize);
                let b = next!(it, usize);
                if uf1.union(a, b) {
                    g[a].push(b); g[b].push(a);
                }
                queries.push(Op::Bridge(a, b));
            },
            "penguins" => {
                let a = next!(it, usize);
                let x = next!(it, i64);
                queries.push(Op::Penguins(a, x));
            },
            "excursion" => {
                let a = next!(it, usize);
                let b = next!(it, usize);
                queries.push(Op::Excursion(a, b));
            },
            _ => unreachable!(),
        }
    }

    for i in 1..=n {
        if uf1.union(1, i) {
            g[1].push(i); g[i].push(1);
        }
    }

    let (mut parent, mut depth, mut size, mut heavy) =
        (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let (mut head, mut pos, mut rev) = 
        (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut rev, &mut cur);

    let mut base = vec![0i64; n];
    for u in 1..=n { base[pos[u]] = val[u]; }
    let mut seg = SegTree::new(n);
    seg.build(&base);

    let mut uf2 = UnionFind::new(n);
    let mut out = String::new();
    for op in queries {
        match op {
            Op::Bridge(a, b) => {
                if uf2.union(a, b) { 
                    out.push_str("yes\n"); 
                } else { out.push_str("no\n"); }
            }
            Op::Penguins(a, x) => {
                seg.update(pos[a], x);
            }
            Op::Excursion(a, b) => {
                if uf2.find(a) != uf2.find(b) {
                    out.push_str("impossible\n");
                } else {
                    let ans = path_sum(a, b, &head, &depth, &parent, &pos, &seg);
                    out.push_str(&format!("{ans}\n"));
                }
            }
        }
    }
    print!("{out}");
}