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

    let s = it.next().unwrap();

    let mut number = 0u32;
    let mut star: Option<usize> = None;

    for (i, c) in s.char_indices() {
        match c {
            '0'..='9' => {
                let d = (c as u8 - b'0') as u32;
                if i % 2 == 0 {
                    number += d;
                } else { number += 3 * d };
            }
            '*' => {
                star = Some(i);
            }
            _ => {}
        }
    }

    number %= 10;
    let need = (10 - number) % 10;

    if star.unwrap() % 2 == 0 {
        println!("{need}");
    } else {
        println!("{}", (need * 7) % 10);
    }
}