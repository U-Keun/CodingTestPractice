use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let n: usize = lines.next().unwrap().unwrap()
        .trim().parse().expect("parse error");

    let cute = lines
        .take(n).filter_map(Result::ok)
        .filter(|line| line.trim() == "1")
        .count();

    let message = if cute * 2 > n {
        "Junhee is cute!"
    } else {
        "Junhee is not cute!"
    };
    println!("{}", message);
}