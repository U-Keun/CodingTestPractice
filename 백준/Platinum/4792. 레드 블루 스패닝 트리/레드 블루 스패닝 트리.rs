use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone, Copy)]
struct Edge { blue: bool, u: usize, v: usize }

struct UnionFind { p: Vec<usize>, r: Vec<u8> }
impl UnionFind {
    fn new(n: usize) -> Self { Self { p: (0..=n).collect(), r: vec![0; n + 1] } }
    fn find(&mut self, x: usize) -> usize {
        if self.p[x] != x { self.p[x] = self.find(self.p[x]); }
        self.p[x]
    }
    fn union(&mut self, a: usize, b: usize) -> bool {
        let (mut a, mut b) = (self.find(a), self.find(b));
        if a == b { return false; }
        if self.r[a] < self.r[b] { std::mem::swap(&mut a, &mut b); }
        self.p[b] = a;
        if self.r[a] == self.r[b] { self.r[a] += 1; }
        true
    }
}

fn count_blue(n: usize, edges: &mut [Edge], blue_first: bool) -> usize {
    edges.sort_by_key(|e| {
        if blue_first {
            if e.blue { 0 } else { 1 }
        } else {
            if e.blue { 1 } else { 0 }
        }
    });
    let mut uf = UnionFind::new(n);
    let (mut used, mut blue_cnt) = (0, 0usize);
    for &e in edges.iter() {
        if uf.union(e.u, e.v) {
            used += 1;
            if e.blue { blue_cnt += 1; }
            if used == n - 1 { break; }
        }
    }
    blue_cnt
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let (mut n, mut m, mut k) =
        (next!(it, usize), next!(it, usize), next!(it, usize));
    let mut out = String::new();
    while n != 0 || m != 0 || k != 0 {
        let mut edges: Vec<Edge> = Vec::new();
        for _ in 0..m {
            let c = it.next().unwrap().chars().next().unwrap();
            let u = next!(it, usize);
            let v = next!(it, usize);
            edges.push(Edge { blue: c == 'B', u, v });
        }

        let mut e1 = edges.clone();
        let mut e2 = edges.clone();
        let min = count_blue(n, &mut e1, false);
        let max = count_blue(n, &mut e2, true);

        if min <= k && k <= max { out.push_str("1\n"); }
        else { out.push_str("0\n"); }

        n = next!(it, usize);
        m = next!(it, usize);
        k = next!(it, usize);
    }
    print!("{out}");
}