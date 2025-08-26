use std::io::{ self, Read };
use std::cmp::min;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

const INF: i64 = i64::MAX / 4;

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let m = next!(it, i64);

    let mut arr: Vec<_> = (0..n)
        .map(|_| next!(it, i64))
        .collect();
    let has_zero = arr.iter().any(|&x| x == 0);

    if !has_zero {
        arr.push(0);
    }
    arr.sort_unstable();
    
    let nn = arr.len();
    let start = arr.binary_search(&0).unwrap();

    let mut best = 0i64;
    let max_k = n - if has_zero { 1 } else { 0 };

    for k in 0..=max_k {
        let mut dp = vec![vec![[INF; 2]; nn]; nn];
        dp[start][start][0] = 0;
        dp[start][start][1] = 0;

        for e in 0..k {
            for l in 0..=(nn - e - 1) {
                let r = l + e;
                let rem = (k - e) as i64;

                let cur0 = dp[l][r][0];
                if cur0 < INF {
                    if l > 0 {
                        let d = (arr[l] - arr[l - 1]).abs();
                        dp[l - 1][r][0] = min(dp[l - 1][r][0], cur0 + rem * d);
                    }
                    if r + 1 < nn {
                        let d = (arr[r + 1] - arr[l]).abs();
                        dp[l][r + 1][1] = min(dp[l][r + 1][1], cur0 + rem * d);
                    }
                }

                let cur1 = dp[l][r][1];
                if cur1 < INF {
                    if r + 1 < nn {
                        let d = (arr[r + 1] - arr[r]).abs();
                        dp[l][r + 1][1] = min(dp[l][r + 1][1], cur1 + rem * d);
                    }
                    if l > 0 {
                        let d = (arr[r] - arr[l - 1]).abs();
                        dp[l - 1][r][0] = min(dp[l - 1][r][0], cur1 + rem * d);
                    }
                }
            }
        }

        let mut melt = INF;
        for l in 0..=(nn - k - 1) {
            let r = l + k;
            melt = melt.min(dp[l][r][0]).min(dp[l][r][1]);
        }
        best = best.max((k as i64) * m - melt);
    }
    let answer = best + if has_zero { m } else { 0 };
    println!("{answer}");
}