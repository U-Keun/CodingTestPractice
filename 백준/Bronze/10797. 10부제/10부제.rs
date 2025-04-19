use std::io;

fn main() {
    let mut input = String::new();

    io::stdin().read_line(&mut input).unwrap();
    let num: u32 = input.trim().parse().unwrap();

    let mut line = String::new();
    io::stdin().read_line(&mut line).unwrap();
    let count = line
        .split_whitespace()
        .map(|x| x.parse::<u32>().unwrap())
        .filter(|x| *x == num)
        .count();

    println!("{}", count);
}