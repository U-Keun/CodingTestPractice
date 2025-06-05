macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let n: usize = read_line!(lines).trim().parse().unwrap();
    let mut stack = Vec::new();

    for _ in 0..n {
        let num: u32 = read_line!(lines).trim().parse().unwrap();
        while stack.last().map_or(false, |&top| top <= num) {
            stack.pop();
        }
        stack.push(num);
    }
    
    println!("{}", stack.len());
}