use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    for line in stdin.lock().lines() {
        match line {
            Ok(text) => println!("{}", text),
            Err(_) => break,
        }
    }
}