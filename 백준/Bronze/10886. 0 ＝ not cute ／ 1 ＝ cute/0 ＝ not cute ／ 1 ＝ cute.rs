use std::io;
use std::str::FromStr;

fn read_line_as<T: FromStr>() -> T
    where T::Err: std::fmt::Debug,
{
    let mut buf = String::new();
    io::stdin()
        .read_line(&mut buf)
        .expect("input error");
    buf.trim().parse().expect("parse error")
}

fn main() {
    let n: u32 = read_line_as();

    let mut cute = 0u32;
    for _ in 0..n {
        let op: u32 = read_line_as();
        if op == 1 {
            cute += 1;
        }
    }

    if cute > n / 2 {
        println!("Junhee is cute!");
    } else {
        println!("Junhee is not cute!");
    }

}