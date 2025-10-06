use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    n: usize,
    tangent: Vec<i64>,
    section: Vec<i64>,
}
impl SegTree {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, tangent: vec![0; n * 2], section:  vec![0; n * 2] }
    }
    fn range_update(&mut self, mut l: usize, mut r: usize, t: i64, s: i64) {
        l += self.n - 1; r += self.n - 1;
        while l <= r {
            if (l & 1) == 1 { self.tangent[l] += t; self.section[l] += s; l += 1; }
            if (r & 1) == 0 { self.tangent[r] += t; self.section[r] += s; r -= 1; }
            l >>= 1; r >>= 1;
        }
    }
    fn point_query(&self, i: usize) -> i64 {
        let mut t = 0i64; let mut s = 0i64;
        let mut p = i + self.n - 1;
        while p > 0 {
            t += self.tangent[p];
            s += self.section[p];
            p >>= 1;
        }
        t * (i as i64) + s
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

fn update_query(mut u: usize, mut v: usize, seg: &mut SegTree,
    head: &[usize], pos: &[usize], parent: &[usize], depth: &[usize]) {
    let mut count = 0usize;
    let mut to_v: Vec<(usize, usize)> = Vec::new();

    while head[u] != head[v] {
        if depth[head[u]] >= depth[head[v]] {
            let l = pos[head[u]]; let r = pos[u];
            seg.range_update(l, r, -1, count as i64 + r as i64);
            count += r - l + 1;
            u = parent[head[u]];
        } else {
            let l = pos[head[v]]; let r = pos[v];
            to_v.push((l, r));
            v = parent[head[v]];
        }
    }

    if depth[u] >= depth[v] {
        let l = pos[v]; let r = pos[u];
        if l <= r {
            seg.range_update(l, r, -1, count as i64 + r as i64);
            count += r - l + 1;
        }
        for &(l, r) in to_v.iter().rev() {
            seg.range_update(l, r, 1, count as i64 - l as i64);
            count += r - l + 1;
        }
    } else {
        to_v.push((pos[u], pos[v]));
        for &(l, r) in to_v.iter().rev() {
            seg.range_update(l, r, 1, count as i64 - l as i64);
            count += r - l + 1;
        }
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut g = vec![vec![]; n + 1];

    for _ in 1..n {
        let a = next!(it, usize);
        let b = next!(it, usize);
        g[a].push(b);
        g[b].push(a);
    }

    let (mut parent, mut depth, mut size, mut heavy) = 
        (vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1], vec![0usize; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy);

    let (mut head, mut pos) =
        (vec![0usize; n + 1], vec![0usize; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut cur);

    let mut seg = SegTree::new(n);
    
    let q = next!(it, usize);
    let mut out = String::new();
    for _ in 0..q {
        let t = next!(it, usize);
        if t == 1 {
            let u = next!(it, usize);
            let v = next!(it, usize);
            update_query(u, v, &mut seg, &head, &pos, &parent, &depth);
        } else {
            let x = next!(it, usize);
            let ans = seg.point_query(pos[x]);
            out.push_str(&format!("{}\n", ans));
        }
    }
    
    print!("{out}");
}