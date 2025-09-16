use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn dfs1(u: usize, p: usize, g: &Vec<Vec<(usize, u64)>>,
    parent: &mut [usize], depth: &mut [usize], size: &mut [usize], heavy: &mut [usize],
    dist: &mut [u64]) {
    parent[u] = p;
    depth[u] = if p == 0 { 0 } else { depth[p] + 1 };
    size[u] = 1;
    heavy[u] = 0;
    let mut best = 0usize;

    for &(v, w) in &g[u] {
        if v == p { continue; }
        dist[v] = dist[u] + w;
        dfs1(v, u, g, parent, depth, size, heavy, dist);
        size[u] += size[v];
        if size[v] > best { best = size[v]; heavy[u] = v; }
    }
}

fn dfs2(u: usize, h: usize, g: &Vec<Vec<(usize, u64)>>,
    parent: &[usize], heavy: &[usize],
    head: &mut [usize], pos: &mut [usize], rev: &mut [usize], cur: &mut usize) {
    head[u] = h;
    pos[u] = *cur;
    rev[*cur] = u;
    *cur += 1;

    if heavy[u] != 0 {
        dfs2(heavy[u], h, g, parent, heavy, head, pos, rev, cur);
    } 
    for &(v, _) in &g[u] {
        if v == parent[u] || v == heavy[u] { continue; }
        dfs2(v, v, g, parent, heavy, head, pos, rev, cur);
    }
} 

fn lca(mut a: usize, mut b: usize,
    head: &[usize], depth: &[usize], parent: &[usize]) -> usize {
    while head[a] != head[b] {
        if depth[head[a]] > depth[head[b]] {
            a = parent[head[a]];
        } else {
            b = parent[head[b]];
        }
    }
    if depth[a] < depth[b] { a } else { b }
}

fn move_up(mut u: usize, mut s: usize,
    head: &[usize], parent: &[usize], pos: &[usize], rev: &[usize]) -> usize {
    while s > 0 {
        let hu = head[u];
        let d = pos[u] - pos[hu];
        if s <= d {
            return rev[pos[u] - s];
        } else {
            s -= d + 1;
            u = parent[hu];
        }
    }
    u
}

fn kth_on_path(u: usize, v: usize, k: usize,
    head: &[usize], depth: &[usize], parent: &[usize],
    pos: &[usize], rev: &[usize]) -> usize {
    let c = lca(u, v, head, depth, parent);
    let du = depth[u] - depth[c];
    if k - 1 <= du {
        move_up(u, k - 1, head, parent, pos, rev)
    } else {
        let dv = depth[v] - depth[c];
        let down = k - 1 - du;
        let from_v = dv - down;
        move_up(v, from_v, head, parent, pos, rev)
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut g = vec![Vec::<(usize, u64)>::new(); n + 1];
    for _ in 1..n {
        let u = next!(it, usize);
        let v = next!(it, usize);
        let w = next!(it, u64);
        g[u].push((v, w));
        g[v].push((u, w));
    }

    let (mut parent, mut depth, mut size, mut heavy, mut dist) =
        (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1], vec![0; n + 1], vec![0u64; n + 1]);
    dfs1(1, 0, &g, &mut parent, &mut depth, &mut size, &mut heavy, &mut dist);

    let (mut head, mut pos, mut rev) =
        (vec![0; n + 1], vec![0; n + 1], vec![0; n + 1]);
    let mut cur = 0usize;
    dfs2(1, 1, &g, &parent, &heavy, &mut head, &mut pos, &mut rev, &mut cur);

    let q = next!(it, usize);
    let mut out = String::new();
    for _ in 0..q {
        let t = next!(it, usize);
        let u = next!(it, usize);
        let v = next!(it, usize);
        if t == 1 {
            let c = lca(u, v, &head, &depth, &parent);
            let ans = dist[u] + dist[v] - 2 * dist[c];
            out.push_str(&format!("{ans}\n"));
        } else {
            let k = next!(it, usize);
            let ans = kth_on_path(u, v, k, &head, &depth, &parent, &pos, &rev);
            out.push_str(&format!("{ans}\n"));
        }

    }
    print!("{out}");
}