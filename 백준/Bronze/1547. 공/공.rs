macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let m: usize = read_line!(lines)
        .trim()
        .parse()
        .unwrap();
    
    let mut cur = 1u8;
    for _ in 0..m {
        let (a, b): (u8, u8) = {
            let line = read_line!(lines);
            let mut nums = line
                .split_whitespace()
                .map(|s| s.parse::<u8>().unwrap());
            (nums.next().unwrap(), nums.next().unwrap())
        };

        if cur == a || cur == b {
            cur = if cur == a { b } else { a };
        }
    }

    println!("{}", cur);
}