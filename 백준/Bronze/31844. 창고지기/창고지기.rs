use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let mut r = 0i8; let mut b = 0i8; let mut d = 0i8;
    for (i, c) in input.chars().enumerate() {
        match c {
            '@' => r = i as i8,
            '#' => b = i as i8,
            '!' => d = i as i8,
            _ => {}
        }
    }

    let mut answer = -1 as i8;
    if r < d {
        if r < b && b < d {
            answer = d - r - 1;
        }
    } else {
        if d < b && b < r {
            answer = r - d - 1;
        }
    }

    println!("{}", answer);
}