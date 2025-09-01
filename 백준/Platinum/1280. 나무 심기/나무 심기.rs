use std::io::{ self, Read };

const MOD: i64 = 1000000007;

struct Fenwick {
    n: usize,
    bit: Vec<i64>
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
    let pos: Vec<i64> = (0..n).map(|_| next!(it, i64)).collect();

    let mut comp = pos.clone();
    comp.sort_unstable();
    let indices: Vec<usize> = pos.iter()
        .map(|&x| comp.binary_search(&x).unwrap() + 1)
        .collect();

    let mut bit_cnt = Fenwick::new(n);
    let mut bit_sum = Fenwick::new(n);

    let mut answer = 1i64;
    let mut total = 0i64;

    for i in 0..n {
        let x = pos[i] % MOD;        
        let idx = indices[i];

        if i > 0 {
            let left_cnt = bit_cnt.sum(idx) % MOD;
            let left_sum = bit_sum.sum(idx) % MOD;

            let right_cnt = ((i as i64) - left_cnt + MOD) % MOD;
            let right_sum = (total - left_sum + MOD) % MOD;

            let mut cost = (x * left_cnt) % MOD;
            cost = (cost - left_sum + MOD) % MOD;
            cost = (cost + right_sum) % MOD;
            cost = (cost - (x * right_cnt) % MOD + MOD) % MOD;

            answer = (answer * cost) % MOD;
        }

        bit_cnt.add(idx, 1);
        bit_sum.add(idx, x);
        total = (total + x) % MOD;
    }
    println!("{}", answer % MOD);
}