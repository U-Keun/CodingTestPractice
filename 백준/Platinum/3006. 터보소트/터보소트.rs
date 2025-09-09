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
    #[inline]
    fn add(&mut self, mut i: usize, val: i64) {
        while i <= self.n {
            self.tree[i] += val;
            i += i & (!i + 1);
        }
    }
    #[inline]
    fn sum(&self, mut i: usize) -> i64 {
        let mut s = 0;
        while i > 0 {
            s += self.tree[i];
            i &= i - 1;
        }
        s
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut pos = vec![0usize; n + 1];
    for i in 1..=n {
        let v = next!(it, usize);
        pos[v] = i;
    }

    let mut fw = Fenwick::new(n);
    for i in 1..=n {
        fw.add(i, 1);
    }

    let mut out = String::new();
    for k in 1..=n {
        if k % 2 == 1 {
            let x = (k + 1) / 2;
            let p = pos[x];
            let left = fw.sum(p) - 1;
            out.push_str(&format!("{}\n", left));
            fw.add(p, -1);
        } else {
            let y = n - (k / 2) + 1;
            let p = pos[y];
            let right = fw.sum(n) - fw.sum(p);
            out.push_str(&format!("{}\n", right));
            fw.add(p, -1);
        }
    }
    print!("{out}");
}