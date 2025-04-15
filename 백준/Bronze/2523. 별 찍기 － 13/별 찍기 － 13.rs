use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let n: u32 = input.trim().parse().unwrap();

    for i in 1..=n {
        let stars = "*".repeat(i as usize);
        println!("{}", stars);
    }

    for i in (1..n).rev() {
        let stars = "*".repeat(i as usize);
        println!("{}", stars);
    }
}