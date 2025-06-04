macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };

fn check(mut num: u32) -> bool {
    let year = [ 2, 0, 2, 3 ];
    let mut idx = 4;

    while num > 0 {
        if idx == 0 {
            break;
        }
        if num % 10 == year[idx - 1] {
            idx -= 1;
        }
        num /= 10;
    }
    idx == 0
}

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let n: u32 = read_line!(lines).trim().parse().unwrap();

    let mut answer = 0u32;
    for i in 2023..=n {
        if check(i) {
            answer += 1;
        }
    }
    
    println!("{}", answer);
}