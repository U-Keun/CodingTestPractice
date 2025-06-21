use std::io::{ self, BufRead };
use std::collections::HashMap;

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let num: usize = lines
        .next().unwrap().unwrap()
        .trim().parse().unwrap();

    let mut map = HashMap::new();
    
    let line = lines.next().unwrap().unwrap();
    let mut iter = line.split_whitespace();

    for _ in 0..num {
        let h: usize = iter.next().unwrap().parse().unwrap();
        let count = map.entry(h).or_insert(0);
        *count += 1;
    }

    let mut answer = 0;
    for (_, &count) in &map {
        answer += count.min(2);
    }

    println!("{}", answer);
}