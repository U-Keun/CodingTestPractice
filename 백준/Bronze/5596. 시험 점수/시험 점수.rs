use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let reader = io::BufReader::new(stdin.lock());

    let mut answer = 0u32;
    for line in reader.lines() {
        let sum: u32 = line.unwrap()
            .split_whitespace()
            .map(|s| s.parse::<u32>().unwrap())
            .sum();

        answer = answer.max(sum);
    }

    println!("{}", answer);
}