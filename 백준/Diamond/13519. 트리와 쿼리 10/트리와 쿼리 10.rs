use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone, Copy, Debug, Default)]
struct Node {
    l: i64, r: i64, // left-end/right-end max
    mx: i64, sum: i64,
}
impl Node {
    #[inline]
    fn refresh(mut self) -> Self {
        if self.l < 0 { self.l = 0; }
        if self.r < 0 { self.r = 0; }
        if self.mx < 0 { self.mx = 0; }
        self
    }
}

#[inline]
fn merge(a: Node, b: Node) -> Node {
    let mut ret = Node {
        l: a.l.max(a.sum + b.l),
        r: (a.r + b.sum).max(b.r),
        mx: a.mx.max(b.mx).max(a.r + b.l),
        sum: a.sum + b.sum,
    };
    ret = ret.refresh();
    ret
}

struct SegTree {
    n: usize,
    tree: Vec<Node>,
    lazy: Vec<Option<i64>>,
}
impl SegTree {
    fn new(n: usize) -> Self {
        Self {
            n,
            tree: vec![Node::default(); n * 4],
            lazy: vec![None; n * 4],
        }
    }
    fn apply(&mut self, idx: usize, s: usize, e: usize, v: i64) {
        let len = (e - s + 1) as i64;
        let mut node = Node { l: v * len, r: v * len, mx: v * len, sum: v * len };
        node = node.refresh();
        self.tree[idx] = node;
        self.lazy[idx] = Some(v);
    }
    fn push(&mut self, idx: usize, s: usize, e: usize) {
        if let Some(v) = self.lazy[idx] {
            if s != e {
                let mid = (s + e) / 2;
                let l = idx << 1; let r = l | 1;
                self.apply(l, s, mid, v);
                self.apply(r, mid + 1, e, v);   
            }
            self.lazy[idx] = None;
        }
    }
    fn build(&mut self, arr: &[i64], idx: usize, s: usize, e: usize) {
        if s == e {
            let v = arr[s];
            let mut node = Node { l: v, r: v, mx: v, sum: v };
            node = node.refresh();
            self.tree[idx] = node;
            return;
        }
        let mid = (s + e) / 2;
        self.build(arr, idx << 1, s, mid);
        self.build(arr, idx << 1 | 1, mid + 1, e);
        self.tree[idx] = merge(self.tree[idx << 1], self.tree[idx << 1 | 1]);
    }
    fn update(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize, v: i64) {
        if r < s || e < l { return; }
        if l <= s && e <= r {
            self.apply(idx, s, e, v);
            return;
        }
        self.push(idx, s, e);
        let mid = (s + e) / 2;
        self.update(idx << 1, s, mid, l, r, v);
        self.update(idx << 1 | 1, mid + 1, e, l, r, v);
        self.tree[idx] = merge(self.tree[idx << 1], self.tree[idx << 1 | 1]);
    }
    fn query(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize) -> Node {
        if r < s || e < l { return Node::default(); }
        if l <= s && e <= r { return self.tree[idx]; }
        self.push(idx, s, e);
        let mid = (s + e) / 2;
        let a = self.query(idx << 1, s, mid, l, r);
        let b = self.query(idx << 1 | 1, mid + 1, e, l, r);
        merge(a, b)
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
        if size[v] > best {
            best = size[v];
            heavy[u] = v;
        }
    }
}

fn dfs2(u: usize, h: usize, g: &Vec<Vec<usize>>,
    parent: &[usize], heavy: &[usize],
    head: &mut [usize], pos: &mut [usize], inv: &mut [usize], cur: &mut usize) {
    head[u] = h;
    pos[u] = *cur;
    inv[*cur] = u;
    *cur += 1;
    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, inv, cur);
    }
    for &v in &g[u] {
        if v == parent[u] || v == heavy[u] { continue; }
        dfs2(v, v, g, parent, heavy, head, pos, inv, cur);
    }
}

fn path_update(mut u: usize, mut v: usize, x: i64, n: usize,
    parent: &[usize], depth: &[usize], head: &[usize], pos: &[usize], 
    seg: &mut SegTree) {
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let h = head[u];
        seg.update(1, 1, n, pos[h], pos[u], x);
        u = parent[h];
    }
    if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
    seg.update(1, 1, n, pos[u], pos[v], x);
}

fn path_query(mut u: usize, mut v: usize, n: usize,
    parent: &[usize], depth: &[usize], head: &[usize], pos: &[usize],
    seg: &mut SegTree) -> i64 {
    let mut left = Node::default();
    let mut right = Node::default();

    while head[u] != head[v] {
        if depth[head[u]] > depth[head[v]] {
            let seg_u = seg.query(1, 1, n, pos[head[u]], pos[u]);
            left = merge(seg_u, left);
            u = parent[head[u]];
        } else {
            let seg_v = seg.query(1, 1, n, pos[head[v]], pos[v]);
            right = merge(seg_v, right);
            v = parent[head[v]];
        }
    }
    if depth[u] > depth[v] {
        let rest = seg.query(1, 1, n, pos[v], pos[u]);
        left = merge(rest, left);
    } else {
        let rest = seg.query(1, 1, n, pos[u], pos[v]);
        right = merge(rest, right);
    }
    
    let Node { l: l1, r: r1, mx, sum } = left;
    let left_rev = Node { l: r1, r: l1, mx, sum };
    merge(left_rev, right).mx
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut g = vec![Vec::new(); n + 1];
    let mut w = vec![0i64; n + 1];
    for i in 1..=n {
        w[i] = next!(it, i64);
    }
    for _ in 1..n {
        let a = next!(it, usize);
        let b = next!(it, usize);
        g[a].push(b);
        g[b].push(a);
    }

    let (mut parent, mut depth, mut size, mut heavy) = 
        (vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let (mut head, mut pos, mut inv) = 
        (vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1]);
    let mut cur = 1usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut inv, &mut cur);

    let mut base = vec![0i64; n + 1];
    for v in 1..=n { base[pos[v]] = w[v]; }

    let mut seg = SegTree::new(n);
    seg.build(&base, 1, 1, n);

    let m = next!(it, usize);
    let mut out = String::new();
    for _ in 0..m {
        let q = next!(it, usize);
        if q == 1 {
            let u = next!(it, usize);
            let v = next!(it, usize);
            out.push_str(&format!("{}\n", path_query(u, v, n, &parent, &depth, &head, &pos, &mut seg)));
        } else {
            let u = next!(it, usize);
            let v = next!(it, usize);
            let w = next!(it, i64);
            path_update(u, v, w, n, &parent, &depth, &head, &pos, &mut seg);
        }
    }
    print!("{out}");
}