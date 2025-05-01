use std::io;

fn main() {
    let n: u32 = {
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();
        input.trim().parse::<u32>().unwrap()
    };

    for i in 0..n {
        let blank = " ".repeat((n - i - 1) as usize);
        let stars = " *".repeat(i as usize);
        println!("{}*{}", blank, stars);
    }
}