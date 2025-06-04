macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();
    
    let a: u32 = read_line!(lines).trim().parse().unwrap();
    let b: u32 = read_line!(lines).trim().parse().unwrap();
    
    if a <= b {
        println!("high speed rail");
    } else {
        println!("flight");
    }
}