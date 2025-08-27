use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn game(it: &mut std::str::SplitWhitespace<'_>) {
    let n = next!(it, usize);
    let arr: Vec<_> = (0..n)
        .map(|_| next!(it, i64))
        .collect();
    
    // dp[i][j] : arr 구간 [i, j]에서 얻을 수 있는 최대 점수차(i <= j)
    let mut dp = vec![vec![0i64; n]; n];
    
    for i in 0..n {
        dp[i][i] = arr[i];
    }

    for len in 2..=n {
        for l in 0..=n - len {
            let r = l + len - 1;
            let left = arr[l] - dp[l + 1][r];
            let right = arr[r] - dp[l][r - 1];
            dp[l][r] = left.max(right);
        }
    }

    let sum: i64 = arr.iter().sum();
    println!("{}", (sum + dp[0][n - 1]) / 2);
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let t = next!(it, usize);
    for _ in 0..t {
        game(&mut it);
    }
}