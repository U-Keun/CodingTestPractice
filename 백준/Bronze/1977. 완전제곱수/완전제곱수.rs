use std::io;
use std::str::FromStr;

fn read_line_as<T: FromStr>() -> T
where
    T::Err: std::fmt::Debug,
{
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).expect("Failed to read line");
    buf.trim().parse::<T>().expect("Failed to parse input")
}

fn is_perfect_square(n: u32) -> bool {
    let root = (n as f64).sqrt() as u32;
    root * root == n
}

fn main() {
    let (n, m): (u32, u32) = (read_line_as(), read_line_as());

    let (mut min, mut sum) = (10000u32, 0u32);
    for i in n..=m {
        if is_perfect_square(i) {
            sum += i;
            min = min.min(i);
        }
    }
    match sum {
        0 => println!("-1"),
        _ => println!("{}\n{}", sum, min),
    }
}