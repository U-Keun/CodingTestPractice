use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct Fenwick {
    n: usize,
    tree: Vec<i64>,
}
impl Fenwick {
    fn new(n: usize) -> Self {
        Self { n, tree: vec![0; n + 1] }
    }
    fn init(&mut self, a: &[i64]) {
        for (i, &v) in a.iter().enumerate() {
            self.add(i + 1, v);
        }
    }
    #[inline]
    fn add(&mut self, mut i: usize, val: i64) {
        while i <= self.n {
            self.tree[i] += val;
            i += i & (!i + 1);
        }
    }
    fn kth_idx(&self, mut k: i64) -> usize {
        let mut idx = 0usize;
        let mut bit = 1usize;
        while bit << 1 <= self.n { bit <<= 1; }
        let mut b = bit;
        while b > 0 {
            let next = idx + b;
            if next <= self.n && self.tree[next] < k {
                k -= self.tree[next];
                idx = next;
            }
            b >>= 1;
        }
        idx + 1
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let a: Vec<i64> = (0..n).map(|_| next!(it, i64)).collect();

    let mut fenwick = Fenwick::new(n);
    fenwick.init(&a);

    let q = next!(it, usize);
    let mut out = String::new();

    for _ in 0..q {
        let t = next!(it, i32);
        if t == 1 {
            let i = next!(it, usize);
            let x = next!(it, i64);
            fenwick.add(i, x);
        } else {
            let k = next!(it, i64);
            let pos = fenwick.kth_idx(k);
            out.push_str(&format!("{}\n", pos));
        }
    }
    
    print!("{out}");
}