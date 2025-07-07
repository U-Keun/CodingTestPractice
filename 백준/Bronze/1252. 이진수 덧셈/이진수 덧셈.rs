use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();

    let mut iter = buf.trim().split_whitespace();
    let mut first: Vec<u8> = iter.next().unwrap()
        .chars().rev()
        .map(|c| c.to_digit(2).unwrap() as u8)
        .collect();
    let mut second: Vec<u8> = iter.next().unwrap()
        .chars().rev()
        .map(|c| c.to_digit(2).unwrap() as u8)
        .collect();

    let max_len = first.len().max(second.len());
    first.resize(max_len, 0);
    second.resize(max_len, 0);

    let mut carry = 0u8;
    let mut result = Vec::with_capacity(max_len + 1);

    for i in 0..max_len {
        let sum = first[i] + second[i] + carry;
        result.push(sum % 2);
        carry = sum / 2;
    }

    if carry > 0 {
        result.push(carry);
    }

    let mut printed = false;
    for k in result.into_iter().rev() {
        if !printed {
            if k == 0 { continue; }
            printed = true;
        }
        print!("{}", k);
    }
    if !printed { print!("0"); }
    println!();
}