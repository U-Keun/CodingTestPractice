use std::io;

fn convert(mut num: u32, base: u32) -> String {
    if num == 0 {
        return "0".to_string();
    }

    let mut digits = Vec::new();
    while num > 0 {
        let rem = num % base;
        let c = if rem < 10 {
            char::from_digit(rem, 10).unwrap()
        } else {
            (b'A' + (rem - 10) as u8) as char
        };
        digits.push(c);
        num /= base;
    }
    digits.iter().rev().collect()
}

fn main() {
    let (m, n): (u32, u32) = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        let mut iter = buf.trim().split_whitespace();
        (
            iter.next().unwrap().parse().unwrap(),
            iter.next().unwrap().parse().unwrap()
        )
    };

    let answer = convert(m, n);
    println!("{}", answer);
}