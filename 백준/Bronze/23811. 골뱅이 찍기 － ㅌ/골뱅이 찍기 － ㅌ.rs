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

    let n = next!(it, usize);
    let long = "@".repeat(n * 5);
    let short = "@".repeat(n);

    for _ in 0..n { println!("{long}");}
    for _ in 0..n { println!("{short}");}
    for _ in 0..n { println!("{long}");}
    for _ in 0..n { println!("{short}");}
    for _ in 0..n { println!("{long}");}
}