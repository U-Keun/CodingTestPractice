use std::io::{ self, Read };

struct Fenwick {
    n: usize,
    bit: Vec<i64>,
}

impl Fenwick {
    fn new(n: usize) -> Self {
        Self { n, bit: vec![0; n + 1] }
    }
    #[inline]
    fn add(&mut self, mut idx: usize, val: i64) {
        while idx <= self.n {
            self.bit[idx] += val;
            idx += idx & (!idx + 1);
        }
    }
    #[inline]
    fn sum(&self, mut idx: usize) -> i64 {
        let mut s = 0i64;
        while idx > 0 {
            s += self.bit[idx];
            idx &= idx - 1;
        }
        s
    }
    #[inline]
    fn range_sum(&self, l: usize, r: usize) -> i64 {
        self.sum(r) - self.sum(l - 1)
    }
}

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let m = next!(it, usize);

    let mut bit = Fenwick::new(n);
    for i in 1..=n {
        let v = next!(it, i64);
        bit.add(i, v);
    }

    let mut out = String::new();
    for _ in 0..m {
        let l = next!(it, usize);
        let r = next!(it, usize);
        out.push_str(&format!("{}\n", bit.range_sum(l, r)));
    }
    print!("{out}");
}