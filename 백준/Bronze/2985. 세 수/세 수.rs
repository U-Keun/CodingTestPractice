use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();

    let mut iter = buf.trim().split_whitespace()
        .map(|s| s.parse::<isize>().unwrap());

    let (a, b, c) = (iter.next().unwrap(), iter.next().unwrap(), iter.next().unwrap());

    let ops: [(char, fn(isize, isize) -> Option<isize>); 4] = [
        ('+', |x, y| Some(x + y)),
        ('-', |x, y| Some(x - y)),
        ('*', |x, y| Some(x * y)),
        ('/', |x, y| if y != 0 { Some(x / y) } else { None }),
    ];

    for &(sym, f) in &ops {
        if f(a, b) == Some(c) {
            println!("{}{}{}={}", a, sym, b, c);
            return;
        }
        if f(b, c) == Some(a) {
            println!("{}={}{}{}", a, b, sym, c);
            return;
        }
    }
}