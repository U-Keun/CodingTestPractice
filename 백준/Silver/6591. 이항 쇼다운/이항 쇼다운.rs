use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn gcd(mut a: u128, mut b: u128) -> u128 {
    while b != 0 {
        let r = a % b;
        a = b;
        b = r;
    }
    a
}

fn binom(n: u64, k: u64) -> u128 {
    if k > n { return 0; }
    let k = std::cmp::min(k, n - k);
    if k == 0 { return 1; }

    let mut num: u128 = 1;
    let mut den: u128 = 1;

    for i in 1..=k {
        num *= (n - k + i) as u128;
        den *= i as u128;

        let g = gcd(num, den);
        num /= g;
        den /= g;
    }

    num
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let mut n: u64 = next!(it, u64);
    let mut k: u64 = next!(it, u64);

    while n != 0 || k != 0 {
        let ans: u128 = binom(n, k);
        println!("{}", ans);
        n = next!(it, u64);
        k = next!(it, u64);
    }
}