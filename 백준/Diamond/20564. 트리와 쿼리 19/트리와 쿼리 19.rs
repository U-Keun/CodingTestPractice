use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    n: usize,
    sum: Vec<i64>,
    lazy: Vec<i64>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, sum: vec![0; n * 2], lazy: vec![0; n * 2] }
    }
    #[inline]
    fn apply(&mut self, idx: usize, add: i64, len: i64) {
        self.sum[idx] += add * len;
        self.lazy[idx] += add;
    }
    #[inline]
    fn push(&mut self, idx: usize, l: usize, r: usize) {
        if self.lazy[idx] != 0 && idx < self.n {
            let mid = (l + r) >> 1;
            let a = self.lazy[idx];
            self.apply(idx << 1, a, (mid - l + 1) as i64);
            self.apply(idx << 1 | 1, a, (r - mid) as i64);
            self.lazy[idx] = 0;
        }
    }
    fn range_update(&mut self, l: usize, r: usize, val: i64) {
        self._update(1, 1, self.n, l, r, val);
    }
    fn _update(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize, v: i64) {
        if r < s || e < l { return; }
        if l <= s && e <= r {
            self.apply(idx, v, (e - s + 1) as i64);
            return;
        }
        self.push(idx, s, e);
        let mid = (s + e) >> 1;
        self._update(idx << 1, s, mid, l, r, v);
        self._update(idx << 1 | 1, mid + 1, e, l, r, v);
        self.sum[idx] = self.sum[idx << 1] + self.sum[idx << 1 | 1];
    }
    fn range_sum(&mut self, l: usize, r: usize) -> i64 {
        self._sum(1, 1, self.n, l, r)
    }
    fn _sum(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize) -> i64 {
        if r < s || e < l { return 0; }
        if l <= s && e <= r { return self.sum[idx]; }
        self.push(idx, s, e);
        let mid = (s + e) >> 1;
        self._sum(idx << 1, s, mid, l, r) + self._sum(idx << 1 | 1, mid + 1, e, l, r)
    }
}

fn dfs1(u: usize, p: usize, g: &Vec<Vec<usize>>, white: &[bool],
    parent: &[usize], depth: &mut [usize], size: &mut [usize], heavy: &mut [usize], sub_w: &mut [i64]) {
    let mut s = if white[u] { 1i64 } else { 0i64 };
    size[u] = 1;
    heavy[u] = 0;
    let mut best = 0usize;
    for &v in &g[u] {
        if v == parent[u] { continue; }
        depth[v] = depth[u] + 1;
        dfs1(v, u, g, white, parent, depth, size, heavy, sub_w);
        if size[v] > best {
            best = size[v];
            heavy[u] = v;
        }
        size[u] += size[v];
        s += sub_w[v];
    }
    sub_w[u] = s;
}

fn dfs2(u: usize, h: usize, g: &Vec<Vec<usize>>,
    parent: &[usize], heavy: &[usize], head: &mut [usize], pos: &mut [usize], cur: &mut usize) {
    head[u] = h;
    *cur += 1; pos[u] = *cur;
    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, cur);
        for &v in &g[u] {
            if v == parent[u] || v == heavy[u] { continue; }
            dfs2(v, v, g, parent, heavy, head, pos, cur);
        }
    }
}

fn path_sum(mut x: usize, root: usize, seg: &mut SegTree,
    head: &[usize], pos: &[usize], parent: &[usize]) -> i64 {
    let mut ans = 0i64;
    while head[x] != head[root] {
        ans += seg.range_sum(pos[head[x]], pos[x]);
        x = parent[head[x]];
    }
    let l = pos[root] + 1;
    let r = pos[x];
    if l <= r { ans += seg.range_sum(l, r); }
    ans
}

fn path_update(mut x: usize, root: usize, d: i64, seg: &mut SegTree,
    head: &[usize], pos: &[usize], parent: &[usize]) {
    while head[x] != head[root] {
        seg.range_update(pos[head[x]], pos[x], d);
        x = parent[head[x]];
    }
    let l = pos[root] + 1;
    let r = pos[x];
    if l <= r { seg.range_update(l, r, d); }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let (n, q) = (next!(it, usize), next!(it, usize));
    let mut white = vec![false; n + 1];
    for i in 1..=n {
        let t = next!(it, i32);
        white[i] = (t == 1);
    }

    let mut g = vec![vec![]; n + 1];
    let mut parent = vec![0usize; n + 1];
    for v in 2..=n {
        let p = next!(it, usize);
        parent[v] = p;
        g[p].push(v);
        g[v].push(p);
    }

    let (mut depth, mut size, mut heavy, mut sub_w) =
        (vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1], vec![0i64; n + 1]);
    dfs1(1, 0, &g, &white, &parent, &mut depth, &mut size, &mut heavy, &mut sub_w);

    let (mut head, mut pos) = 
        (vec![0usize; n + 1], vec![0usize; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut cur);

    let mut seg = SegTree::new(n);
    for v in 2..=n {
        let p = pos[v];
        if p >= 1 { seg.range_update(p, p, sub_w[v]); }
    }

    let mut ans = 0i64;
    for v in 2..=n {
        let w = sub_w[v];
        ans += w * (w - 1) / 2;
    }

    let mut out = String::new();
    out.push_str(&format!("{}\n", ans));
    
    for _ in 0..q {
        let x = next!(it, usize);
        let anc = path_sum(x, 1, &mut seg, &head, &pos, &parent);
        if white[x] { ans += depth[x] as i64 - anc; }
        else { ans += anc; }

        let d: i64 = if white[x] { -1 } else { 1 };
        path_update(x, 1, d, &mut seg, &head, &pos, &parent);

        white[x] = !white[x];

        out.push_str(&format!("{}\n", ans));
    }
    print!("{out}");
}