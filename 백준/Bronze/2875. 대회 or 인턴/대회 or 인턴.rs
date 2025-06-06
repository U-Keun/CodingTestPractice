macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let (n, m, k): (i32, i32, i32) = {
        let line = read_line!(lines);
        let mut nums = line
            .trim()
            .split_whitespace()
            .map(|s| s.parse().unwrap());
        (nums.next().unwrap(), nums.next().unwrap(), nums.next().unwrap())
    };

    let answer = (n / 2).min(m);
    let rest = 0i32.max(k - (n - answer * 2) - (m - answer));

    println!("{}", answer - (rest + 2) / 3);
}