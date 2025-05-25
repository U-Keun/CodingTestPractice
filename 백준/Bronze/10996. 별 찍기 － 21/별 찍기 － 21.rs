use std::io;

fn main() {
    let n = read_num();

    for _ in 0..n {
        draw_star(n);
    }
}

fn draw_star(n: u32) {
    let first = "* ".repeat(((n + 1) / 2) as usize);
    let second = " *".repeat((n / 2) as usize);
    if n == 1 {
        println!("{}", first);
    } else {
        println!("{}\n{}", first, second);
    }
}

fn read_num() -> u32 {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    buf.trim().parse::<u32>().unwrap()
}