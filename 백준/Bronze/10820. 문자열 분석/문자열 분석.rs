use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let reader = io::BufReader::new(stdin.lock());

    for line in reader.lines() {
        let [mut a, mut b, mut c, mut d] = [0u32; 4];

        for ch in line.unwrap().chars() {
            if ch.is_ascii_lowercase() {
                a += 1;
            } else if ch.is_ascii_uppercase() {
                b += 1;
            } else if ch.is_ascii_digit() {
                c += 1;
            } else if ch.is_whitespace() {
                d += 1;
            }
        }

        println!("{} {} {} {}", a, b, c, d);
    }
}