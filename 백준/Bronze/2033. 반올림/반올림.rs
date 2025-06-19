use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).expect("Failed to read a line");
    
    let mut n: usize = buf.trim().parse().expect("Failed parsing");
    let mut d: usize = 10;
    while n > d {
        n = ((n + d / 2) / d) * d;
        d = d * 10;
    }

    println!("{}", n);
}