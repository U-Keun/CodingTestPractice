use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let n = lines.next();

    let (sum, cnt) = lines.next().unwrap().unwrap()
        .split_whitespace()
        .map(|s| s.parse::<i32>().unwrap())
        .fold((0, 0), |(sum, cnt), x| {
            if x == 1 {
                (sum + cnt + 1, cnt + 1)
            } else {
                (sum, 0)
            }
        });

    println!("{}", sum);
}