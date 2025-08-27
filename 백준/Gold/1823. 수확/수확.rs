use std::io::{ self, Read };

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
    let arr: Vec<i64> = (0..n).map(|_| next!(it, i64)).collect();

    let mut dp = vec![vec![0i64; n]; n];

    for i in 0..n {
        dp[i][i] = (n as i64) * arr[i];
    }

    for len in 2..=n {
        let day = (n - len + 1) as i64;
        for l in 0..= n - len {
            let r = l + len - 1;
            let left = arr[l] * day + dp[l + 1][r];
            let right = arr[r] * day + dp[l][r - 1];
            dp[l][r] = left.max(right);
        }
    }

    println!("{}", dp[0][n - 1]);
}