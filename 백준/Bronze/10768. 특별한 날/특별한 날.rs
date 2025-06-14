use std::io::{self, BufRead};

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let month: u32 = lines
        .next().unwrap().unwrap()
        .trim().parse().unwrap();

    let day: u32 = lines
        .next().unwrap().unwrap()
        .trim().parse().unwrap();

    let result = if month < 2 || (month == 2 && day < 18) {
        "Before"
    } else if month == 2 && day == 18 {
        "Special"
    } else {
        "After"
    };

    println!("{}", result);
}