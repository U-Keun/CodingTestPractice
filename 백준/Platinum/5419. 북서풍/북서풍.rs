use std::io::{ self, Read };
use std::cmp::Ordering;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone, Copy)]
struct Point {
    x: i64,
    y: i64,
}

struct Fenwick {
    n: usize,
    bit: Vec<i32>,
}

impl Fenwick {
    fn new(n: usize) -> Self {
        Self { n, bit: vec![0; n + 1] }
    }
    fn add(&mut self, mut idx: usize, val: i32) {
        while idx <= self.n {
            self.bit[idx] += val;
            idx += idx & (!idx + 1);
        }
    }
    fn sum(&self, mut idx: usize) -> i64 {
        let mut s: i64 = 0;
        while idx > 0 {
            s += self.bit[idx] as i64;
            idx &= idx - 1;
        }
        s
    }
    fn suffix_sum(&self, l: usize) -> i64 {
        self.sum(self.n) - self.sum(l.saturating_sub(1))
    }
}

fn compress(points: &mut [Point]) -> Vec<usize> {
    let mut y: Vec<i64> = points.iter().map(|p| p.y).collect();
    y.sort_unstable();
    points.iter().map(|p| {
        y.binary_search(&p.y).unwrap() + 1
    }).collect()
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let t = next!(it, usize);
    let mut out = String::new();

    for _ in 0..t {
        let n = next!(it, usize);
        let mut pts = Vec::with_capacity(n);
        for _ in 0..n {
            let x = next!(it, i64);
            let y = next!(it, i64);
            pts.push(Point { x, y });
        }

        pts.sort_by(|a, b| {
            match a.x.cmp(&b.x) {
                Ordering::Less => Ordering::Less,
                Ordering::Greater => Ordering::Greater,
                Ordering::Equal => b.y.cmp(&a.y),
            }
        });

        let y_idx = compress(&mut pts);
        let mut bit = Fenwick::new(y_idx.iter().copied().max().unwrap_or(0));
        let mut ans = 0i64;
        for &yi in &y_idx {
            ans += bit.suffix_sum(yi);
            bit.add(yi, 1);
        }
        out.push_str(&format!("{ans}\n"));
    }
    print!("{out}");
}