use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut count = 0u32;
    let mut answer = 0u32;
    
    for line in stdin.lock().lines().take(4) {
        let input = line.unwrap();
        let mut parts = input
            .split_whitespace()
            .map(|s| s.parse::<u32>().unwrap());

        let _out = parts.next().unwrap();
        let _in = parts.next().unwrap();

        count = count - _out + _in;
        answer = answer.max(count);
    }

    println!("{}", answer);
}