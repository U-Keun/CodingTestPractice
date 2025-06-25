use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let line = lines.next().unwrap().unwrap();
    let mut iter = line
        .split_whitespace();

    let n: usize = iter.next().unwrap().parse().unwrap();
    let u: usize = iter.next().unwrap().parse().unwrap();
    let l: usize = iter.next().unwrap().parse().unwrap();

    if n >= 1000 {
        if u >= 8000 || l >= 260 {
            println!("Very Good");
        } else { println!("Good"); }
    } else {
        println!("Bad");
    }
}