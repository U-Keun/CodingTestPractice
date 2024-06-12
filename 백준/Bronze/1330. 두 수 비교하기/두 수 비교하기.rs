use std::io;

fn main() {
    let mut s = String::new();

    io::stdin().read_line(&mut s).unwrap();

    let numbers : Vec<i16> = s
        .as_mut_str()
        .split_whitespace()
        .map(|s| s.parse().unwrap())
        .collect();

    if numbers[0] < numbers[1] {
        println!("{}", "<");
    } else if numbers[0] > numbers[1] {
        println!("{}", ">");
    } else {
        println!("{}", "==");
    }
}