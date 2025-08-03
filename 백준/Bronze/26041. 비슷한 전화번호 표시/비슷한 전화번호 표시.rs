use std::io::{ self, Read };

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();

    let mut tokens: Vec<&str> = buf.split_whitespace().collect();

    let prefix = tokens.pop().unwrap();

    let cnt = tokens.iter().filter(|&&w| w.starts_with(prefix) && w != prefix).count();

    println!("{cnt}");
}