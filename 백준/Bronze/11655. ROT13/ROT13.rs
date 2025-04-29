use std::io;

fn rot13_char(c: char) -> char {
    match c {
        'a'..='m' | 'A'..='M' => ((c as u8) + 13) as char,
        'n'..='z' | 'N'..='Z' => ((c as u8) - 13) as char,
        _ => c,
    }
}

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("input error");

    let encoded: String = input
        .chars().map(rot13_char)
        .collect();

    println!("{}", encoded);
}