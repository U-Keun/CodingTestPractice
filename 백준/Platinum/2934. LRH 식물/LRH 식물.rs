use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct Lazy {
    n: usize,
    lazy: Vec<i64>,
}
impl Lazy {
    fn new(len: usize) -> Self {
        let mut n = 1usize;
        while n < len { n <<= 1; }
        Self { n, lazy: vec![0; n * 2] }
    }
    fn range_update(&mut self, l: usize, r: usize, v: i64) {
        self._update(1, 0, self.n, l, r + 1, v);
    }
    fn _update(&mut self, idx: usize, s: usize, e: usize, l: usize, r: usize, v: i64) {
        if r <= s || e <= l { return; }
        if l <= s && e <= r {
            self.lazy[idx] += v;
            return;
        }
        let mid = (s + e) / 2;
        self._update(idx * 2, s, mid, l, r, v);
        self._update(idx * 2 + 1, mid, e, l, r, v);
    }
    fn point_query(&self, pos: usize) -> i64 {
        self._query(1, 0, self.n, pos, 0)
    }
    fn _query(&self, idx: usize, s: usize, e: usize, pos: usize, ans: i64) -> i64 {
        if s + 1 == e { return ans + self.lazy[idx]; }
        let mid = (s + e) / 2;
        if pos < mid {
            self._query(idx * 2, s, mid, pos, ans + self.lazy[idx])
        } else {
            self._query(idx * 2 + 1, mid, e, pos, ans + self.lazy[idx])
        }
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut seg = Lazy::new(100001);
    let mut out = String::new();
    for _ in 0..n {
        let l = next!(it, usize);
        let r = next!(it, usize);

        let s1 = seg.point_query(l);
        let s2 = seg.point_query(r);
        out.push_str(&format!("{}\n", s1 + s2));

        seg.range_update(l + 1, r - 1, 1);

        if s1 != 0 { seg.range_update(l, l, -s1); }
        if s2 != 0 { seg.range_update(r, r, -s2); }
    }
    print!("{out}");
}