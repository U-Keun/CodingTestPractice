use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    let t: u32 = buf.trim().parse().unwrap();

    let mut answer = 0u32;
    for _ in 0..t {
        answer = answer.max(get_score());
    }
    
    println!("{}", answer);
}

fn read_numbers() -> (u32, u32, u32) {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    let mut iter = buf.trim().split_whitespace();

    let a = iter.next().unwrap().parse().unwrap();
    let b = iter.next().unwrap().parse().unwrap();
    let c = iter.next().unwrap().parse().unwrap();

    (a, b, c)
}

fn get_score() -> u32 {
    let (x, y, z) = read_numbers();

    if x == y && y == z {
        10_000 + 1_000 * x
    } else if x == y || x == z {
        1_000 + 100 * x
    } else if y == z {
        1_000 + 100 * y
    } else {
        x.max(y).max(z) * 100
    }
}