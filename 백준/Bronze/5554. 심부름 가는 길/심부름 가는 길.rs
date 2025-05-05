use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let sec: u32 = stdin
        .lock()
        .lines()
        .map(|line| line.unwrap().trim().parse::<u32>().unwrap())
        .sum();

    println!("{}\n{}", sec / 60, sec % 60);

}