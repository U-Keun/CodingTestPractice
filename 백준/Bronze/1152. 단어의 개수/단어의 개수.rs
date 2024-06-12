use std::io;

fn main() {
    let mut s = String::new();

    io::stdin().read_line(&mut s).unwrap();

    let count = s.split_whitespace()
        .count();

    println!("{}", count);
}