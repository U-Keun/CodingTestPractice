use std::io:: { self, BufRead };

fn main() {
    let stdin = io::stdin();
    let (mut idx, mut score) = (0u32, 0u32);

    let mut order = 1u32;
    for line in stdin.lock().lines().take(5) {
        let input = line.unwrap();
        let cur: u32 = input
            .split_whitespace()
            .map(|num| num.parse::<u32>().unwrap())
            .sum();

        if cur > score {
            score = cur;
            idx = order;
        }

        order += 1;
    }

    println!("{} {}", idx, score);
}