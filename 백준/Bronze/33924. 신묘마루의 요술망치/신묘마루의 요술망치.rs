use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let mut n = next!(it, usize);
    let mut m = next!(it, usize);
    let _k = next!(it, usize);

    let s = it.next().unwrap();

    for ch in s.chars() {
        match ch {
            'A' => {
                n = if n + 2 > 4 { (n + 2) % 4 } else { n + 2 };
            }
            'B' => {
                if n <= 2 {
                    n = if n + 1 > 2 { 1 } else { 2 };
                } else {
                    n = if n + 1 > 4 { 3 } else { 4 };
                }
                m = if m + 1 > 2 { 1 } else { 2 };
            }
            'C' => {
                n = 5 - n;
                m = if m + 1 > 2 { 1 } else { 2 };
            }
            'D' => {
                if m == 1 {
                    if n == 1 {
                        m = 2;
                    } else {
                        n -= 1;
                    }
                } else {
                    if n == 4 {
                        m = 1;
                    } else {
                        n += 1;
                    }
                }
            }
            _ => {}
        }
    }
    println!("{n} {m}");
}