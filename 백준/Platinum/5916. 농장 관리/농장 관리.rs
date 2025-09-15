use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegLazy {
    n: usize,
    tree: Vec<i64>,
    lazy: Vec<i64>,
}
impl SegLazy {
    fn new(n: usize) -> Self {
        Self { n, tree: vec![0; 4 * n], lazy: vec![0; 4 * n] }
    }
    #[inline]
    fn push(&mut self, idx: usize, s: usize, e: usize) {
        if self.lazy[idx] == 0 { return; }
        let add = self.lazy[idx];
        self.tree[idx] += add * (e as i64 - s as i64 + 1);
        if s != e {
            self.lazy[idx << 1] += add;
            self.lazy[idx << 1 | 1] += add;
        }
        self.lazy[idx] = 0;
    }
    fn add(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize, v: i64) {
        self.push(idx, s, e);
        if r < s || e < l { return; }
        if l <= s && e <= r {
            self.lazy[idx] += v;
            self.push(idx, s, e);
            return;
        }
        let mid = (s + e) / 2;
        self.add(idx << 1, s, mid, l, r, v);
        self.add(idx << 1 | 1, mid + 1, e, l, r, v);
        self.tree[idx] = self.tree[idx << 1] + self.tree[idx << 1 | 1];
    }
    fn sum(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize) -> i64 {
        self.push(idx, s, e);
        if r < s || e < l { return 0; }
        if l <= s && e <= r { return self.tree[idx]; }
        let mid = (s + e) >> 1;
        self.sum(idx << 1, s, mid, l, r) + self.sum(idx << 1 | 1, mid + 1, e, l, r)
    }
}

fn dfs1(u: usize, p: usize, g: &Vec<Vec<usize>>,
    parent: &mut [usize], depth: &mut [usize],
    size: &mut [usize], heavy: &mut [usize]) {
    parent[u] = p;
    depth[u] = if p == 0 { 0 } else { depth[p] + 1 };
    size[u] = 1;
    let mut best = 0usize;
    heavy[u] = 0;
    for &v in &g[u] {
        if v == p { continue; }
        dfs1(v, u, g, parent, depth, size, heavy);
        size[u] += size[v];
        if size[v] > best { best = size[v]; heavy[u] = v; }
    }
}

fn dfs2(mut u: usize, h: usize, g: &Vec<Vec<usize>>,
    parent: &[usize], heavy: &[usize],
    head: &mut [usize], pos: &mut [usize], cur: &mut usize) {
    head[u] = h;
    *cur += 1;
    pos[u] = *cur;
    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, cur);
    }
    for &v in &g[u] {
        if v == parent[u] || v == heavy[u] { continue; }
        dfs2(v, v, g, parent, heavy, head, pos, cur);
    }
}

fn plant(mut u: usize, mut v: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize],
    seg: &mut SegLazy, n: usize, val: i64) {
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let hu = head[u];
        seg.add(1, 1, n, pos[hu], pos[u], val);
        u = parent[hu];
    }
    if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
    if u != v {
        seg.add(1, 1, n, pos[u] + 1, pos[v], val);
    }
}

fn count(mut u: usize, mut v: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize],
    seg: &mut SegLazy, n: usize) -> i64 {
    let mut answer = 0i64;
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let hu = head[u];
        answer += seg.sum(1, 1, n, pos[hu], pos[u]);
        u = parent[hu];
    }
    if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
    if u != v {
        answer += seg.sum(1, 1, n, pos[u] + 1, pos[v]);
    }
    answer
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let m = next!(it, usize);

    let mut g = vec![Vec::<usize>::new(); n + 1];
    for _ in 1..n {
        let a = next!(it, usize);
        let b = next!(it, usize);
        g[a].push(b); g[b].push(a);
    }

    let (mut parent, mut depth, mut size, mut heavy) =
        (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let (mut head, mut pos) = (vec![0; n + 1], vec![0; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut cur);

    let mut seg = SegLazy::new(n);

    let mut out = String::new();
    for _ in 0..m {
        let q: String = next!(it, String);
        let u = next!(it, usize);
        let v = next!(it, usize);
        match &q[..] {
            "P" => {
                plant(u, v, &head, &depth, &parent, &pos, &mut seg, n, 1);
            }
            "Q" => {
                let ans = count(u, v, &head, &depth, &parent, &pos, &mut seg, n);
                out.push_str(&format!("{ans}\n"));
            }
            _ => unreachable!(),
        }
    }
    print!("{out}");
}