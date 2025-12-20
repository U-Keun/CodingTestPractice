use std::io::{ self, Read };

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();

    let mut lines = buf.lines();

    let first = lines.next().unwrap_or("");
    let mut it = first.split_whitespace();
    let p: usize = it.next().unwrap().parse().unwrap();
    let w: usize = it.next().unwrap().parse().unwrap();

    let s = lines.next().unwrap_or("").trim_end_matches(&['\n', '\r'][..]);

    let groups = ["ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"];
    let mut key_of = [0usize; 26];
    let mut press_of = [0usize; 26];

    for (i, g) in groups.iter().enumerate() {
        let key = i + 2;
        for (j, c) in g.chars().enumerate() {
            let idx = (c as u8 - b'A') as usize;
            key_of[idx] = key;
            press_of[idx] = j + 1;
        }
    }

    let mut answer = 0usize;
    let mut prev = 0usize;

    for c in s.chars() {
        if c == ' ' {
            answer += p;
            prev = 1;
            continue;
        }

        let idx = (c as u8 - b'A') as usize;
        let key = key_of[idx];
        let presses = press_of[idx];

        if prev == key { answer += w; }

        answer += presses * p;
        prev = key;
    }
    println!("{answer}");
}