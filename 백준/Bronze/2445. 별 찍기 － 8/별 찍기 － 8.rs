use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let num: u32 = input.trim().parse().unwrap();

    for i in 1..num + 1 {
        let stars = "*".repeat(i as usize);
        let spaces = " ".repeat(2 * (num - i) as usize);
        println!("{}{}{}", stars, spaces, stars);
    }

    for i in 1..num {
        let stars = "*".repeat((num - i) as usize);
        let spaces = " ".repeat(2 * i as usize);
        println!("{}{}{}", stars, spaces, stars);
    }
}