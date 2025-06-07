macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    loop {
        let line = read_line!(lines);
        let nums: Vec<u8> = line
            .split_whitespace()
            .map(|s| s.parse().unwrap())
            .collect();

        if nums[0] == 0 && nums[1] == 0 {
            break;
        }

        println!("{}", nums[0] + nums[1]);
    }
}