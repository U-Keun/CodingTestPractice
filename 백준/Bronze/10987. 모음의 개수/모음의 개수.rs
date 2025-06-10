use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();

    let vowels = ['a', 'e', 'i', 'o', 'u'];
    let count = buf.trim().chars()
        .filter(|c| vowels.contains(&c.to_ascii_lowercase()))
        .count();

    println!("{}", count);
}