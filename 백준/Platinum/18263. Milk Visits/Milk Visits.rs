use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn merge(a: &[u32], b: &[u32]) -> Vec<u32> {
    let mut v = Vec::with_capacity(a.len() + b.len());
    let (mut i, mut j) = (0usize, 0usize);
    let mut last: Option<u32> = None;

    let mut push_unique = |x: u32, out: &mut Vec<u32>, last: &mut Option<u32>| {
        if Some(x) != *last {
            out.push(x);
            *last = Some(x);
        }
    };

    while i < a.len() && j < b.len() {
        if a[i] < b[j] { push_unique(a[i], &mut v, &mut last); i += 1; }
        else if a[i] > b[j] { push_unique(b[j], &mut v, &mut last); j += 1; }
        else { push_unique(a[i], &mut v, &mut last); i += 1; j += 1; }
    }

    while i < a.len() { push_unique(a[i], &mut v, &mut last); i += 1; }
    while j < b.len() { push_unique(b[j], &mut v, &mut last); j += 1; }
    v
}

struct MSTree {
    n: usize,
    tree: Vec<Vec<u32>>,
}
impl MSTree {
    fn new(base: &[u32]) -> Self {
        let mut n = 1usize;
        while n < base.len() { n <<= 1; }
        let mut tree = vec![Vec::<u32>::new(); n * 2];
        for i in 0..base.len() {
            tree[n + i] = vec![base[i]];
        }
        for i in (1..n).rev() {
            let (left, right) = (&tree[i << 1], &tree[i << 1 | 1]);
            tree[i] = merge(left, right);
        }
        Self { n, tree }
    }
    #[inline]
    fn node_contains(v: &Vec<u32>, t: u32) -> bool {
        let (mut l, mut h) = (0usize, v.len());
        while l < h {
            let mid = (l + h) >> 1;
            if v[mid] < t { l = mid + 1; } else { h = mid; }
        }
        l < v.len() && v[l] == t
    }
    fn exists(&self, mut l: usize, mut r: usize, t: u32) -> bool {
        l += self.n; r += self.n;
        while l <= r {
            if (l & 1) == 1 {
                if Self::node_contains(&self.tree[l], t) { return true; }
                l += 1;
            }
            if (r & 1) == 0 {
                if Self::node_contains(&self.tree[r], t) { return true; }
                if r == 0 { break; }
                r -= 1;
            }
            l >>= 1; r >>= 1;
        }
        false
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
        for &v in &g[u] {
            if v != parent[u] && v != heavy[u] {
                dfs2(v, v, g, parent, heavy, head, pos, cur);
            }
        }
    }
}

fn is_happy(mut u: usize, mut v: usize, t: u32,
    head: &[usize], pos: &[usize], depth: &[usize], parent: &[usize], mst: &MSTree) -> bool {
    while head[u] != head[v] {
        if depth[head[u]] < depth[head[v]] { std::mem::swap(&mut u, &mut v); }
        let hu = head[u];
        if mst.exists(pos[hu], pos[u], t) { return true; }
        u = parent[hu];
    }
    if depth[u] > depth[v] { std::mem::swap(&mut u, &mut v); }
    mst.exists(pos[u], pos[v], t)
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let m = next!(it, usize);

    let mut cows = vec![0u32; n + 1];
    for i in 1..=n { cows[i] = next!(it, u32); }

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
    for u in 1..=n { base[pos[u]] = cows[u]; }

    let mst = MSTree::new(&base);

    let mut out = String::new();
    for _ in 0..m {
        let u = next!(it, usize);
        let v = next!(it, usize);
        let t = next!(it, u32);
        let is_happy = is_happy(u, v, t, &head, &pos, &depth, &parent, &mst);
        out.push(if is_happy { '1' } else { '0' });
    }
    print!("{out}");
}