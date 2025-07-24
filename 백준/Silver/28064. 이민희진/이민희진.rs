use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn check(a: &str, b: &str) -> bool {
    let a_chars: Vec<char> = a.chars().collect();
    let b_chars: Vec<char> = b.chars().collect();
    let n = a_chars.len().min(b_chars.len());

    for k in 1..=n {
        if a_chars[..k] == b_chars[b_chars.len() - k..] { return true; }
        if a_chars[a_chars.len() - k..] == b_chars[..k] { return true; }
    }
    false
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n: usize = next!(it, usize);

    let mut words = Vec::with_capacity(n);
    for _ in 0..n {
        words.push(it.next().unwrap());
    }

    let mut cnt = 0;
    for i in 0..n {
        for j in i + 1..n {
            if check(&words[i], &words[j]) {
                cnt += 1;
            }
        }
    }
    println!("{cnt}");
}