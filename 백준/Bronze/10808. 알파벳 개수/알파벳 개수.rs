use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let input = input.trim();

    let mut counts = [0; 26];

    for c in input.chars() {
        counts[(c as u8 - b'a') as usize] += 1;
    }

    for num in counts {
        print!("{} ", num);
    }
    println!();
}