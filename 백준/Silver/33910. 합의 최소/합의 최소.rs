use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let n: usize = lines.next().unwrap().unwrap()
        .trim().parse().unwrap();

    let nums = lines.next().unwrap().unwrap();
    let mut iter = nums
        .split_whitespace()
        .rev()
        .map(|s| s.parse::<usize>().unwrap());

    let mut sum: u64 = if let Some(first) = iter.next() {
        first as u64
    } else { 0 };

    let mut prev = sum as usize;
    for cur in iter {
        let add = prev.min(cur) as u64;
        sum += add;
        prev = prev.min(cur);
    }

    println!("{}", sum);
}