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
    let mut a = vec![0usize; n];
    for i in 0..n { a[i] = next!(it, usize); }

    let mut dp = vec![0usize; n];
    for i in 0..n {
        dp[i] = a[i];
        for j in 0..i {
            if a[j] > a[i] {
                dp[i] = dp[i].max(dp[j] + a[i]);
            }
        }
    }

    let mut ans = 0usize;
    for i in 0..n {
        ans = ans.max(dp[i]);
    }
    
    println!("{}", ans);
}