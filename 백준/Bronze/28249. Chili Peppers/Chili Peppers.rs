macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };
use std::collections::HashMap;

fn main() {
    let map = HashMap::from([
        ("Poblano", 1500),
        ("Mirasol", 6000),
        ("Serrano", 15500),
        ("Cayenne", 40000),
        ("Thai", 75000),
        ("Habanero", 125000),
    ]);

    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let num: u32 = read_line!(lines)
        .parse()
        .unwrap();

    let mut answer = 0;
    for _ in 0..num {
        let line = read_line!(lines);
        answer += match map.get(line.as_str()) {
            Some(&v) => v,
            None => 0,
        };
    }

    println!("{}", answer);
}