use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let total: u32 = {
        let mut buf = String::new();
        stdin.read_line(&mut buf).unwrap();
        buf.trim().parse().unwrap()
    };

    let receipt: u32 = stdin.lock()
        .lines()
        .map(|line| line.unwrap().trim().parse::<u32>().unwrap())
        .sum();

    println!("{}", total - receipt);
}