use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    n: usize,
    tree: Vec<i32>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tree: vec![-1; n * 2] }
    }
    fn toggle(&mut self, mut idx: usize) {
        idx += self.n;
        if self.tree[idx] == -1 {
            self.tree[idx] = (idx - self.n) as i32;
        } else {
            self.tree[idx] = -1;
        }
        idx >>= 1;
        while idx > 0 {
            let a = self.tree[idx << 1];
            let b = self.tree[idx << 1 | 1];
            self.tree[idx] = if a != -1 { a } else { b };
            idx >>= 1;
        }
    }
    fn query(&self, l: usize, r: usize) -> Option<usize> {
        fn rec(seg: &SegTree, cur: usize, s: usize, e: usize, l: usize, r: usize) -> i32 {
            if r <= s || e <= l || seg.tree[cur] == -1 { return -1; }
            if l <= s && e <= r { return seg.tree[cur]; }
            let mid = (s + e) / 2;
            let left = rec(seg, cur << 1, s, mid, l, r);
            if left != -1 { return left; }
            rec(seg, cur << 1 | 1, mid, e, l, r)
        }
        let pos = rec(self, 1, 0, self.n, l, r);
        if pos == -1 { None } else { Some(pos as usize) }
    }
}

fn dfs1(u: usize, p: usize, g: &Vec<Vec<usize>>,
    parent: &mut [usize], depth: &mut [usize],
    size: &mut [usize], heavy: &mut [usize]) {
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

fn first_black(mut x: usize,
    head: &[usize], depth: &[usize], parent: &[usize], pos: &[usize], rev: &[usize], seg: &SegTree) -> i32 {
    let root = 1usize;
    let mut parts: Vec<(usize, usize)> = Vec::new();
    while head[x] != head[root] {
        let h = head[x];
        parts.push((pos[h], pos[x] + 1));
        x = parent[h];
    }
    parts.push((pos[root], pos[x] + 1));

    for &(l, r) in parts.iter().rev() {
        if let Some(p) = seg.query(l, r) {
            return rev[p] as i32;
        }
    }
    -1
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
        (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let (mut head, mut pos, mut rev) = (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut rev, &mut cur);

    let mut seg = SegTree::new(n);
    
    let q = next!(it, usize);
    let mut out = String::new();
    for _ in 0..q {
        let op = next!(it, usize);
        let x = next!(it, usize);
        if op == 1 {
            seg.toggle(pos[x]);
        } else {
            let ans = first_black(x, &head, &depth, &parent, &pos, &rev, &seg);
            out.push_str(&format!("{ans}\n"));
        }
    }
    print!("{out}");
}