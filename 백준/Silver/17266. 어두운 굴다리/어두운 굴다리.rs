use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let n: usize = lines.next().unwrap().unwrap()
        .trim().parse().unwrap();
    let m: usize = lines.next().unwrap().unwrap()
        .trim().parse().unwrap();

    let line = lines.next().unwrap().unwrap();
    let mut iter = line
        .trim().split_whitespace()
        .map(|s| s.parse::<usize>().unwrap());

    let mut answer = iter.next().unwrap();

    let mut prev = answer;

    for _ in 1..m {
        let cur = iter.next().unwrap();
        let half = (cur - prev + 1) / 2;
        answer = answer.max(half);
        prev = cur;
    }

    answer = answer.max(n - prev);

    println!("{}", answer);
}