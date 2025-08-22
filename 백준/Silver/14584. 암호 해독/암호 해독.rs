use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn shift_string(s: &str, k: u8) -> String {
    s.chars()
        .map(|c| {
            (((c as u8 - b'a' + k) % 26) + b'a') as char
        })
        .collect()
}

fn contains_any_shift(s: &str, word: &str) -> Option<u8> {
    for k in 0..26u8 {
        if word.contains(&shift_string(s, k)) {
            return Some(k);
        }
    }
    None
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let word = it.next().unwrap();

    let n = next!(it, usize);
    for _ in 0..n {
        let s = it.next().unwrap();
        match contains_any_shift(s, word) {
            Some(k) => {
                println!("{}", shift_string(word, 26 - k));
                return;
            },
            None => continue,
        }
    }
}