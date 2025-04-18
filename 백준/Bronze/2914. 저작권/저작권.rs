use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let nums: Vec<u32> = input
        .split_whitespace()
        .map(|x| x.parse().unwrap())
        .collect();

    let answer = nums[0] * (nums[1] - 1) + 1;
    println!("{}", answer);
}