use std::io::{ self, Read };

macro_rules! next {
    ($iter: expr, $t: ty) => {
        $iter.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut it = input.split_whitespace();

    let n: usize = next!(it, usize);
    for i in 0..n {
        for j in 1..n {
            let _ = it.next().unwrap();
        }
        if i == n - 1 {
            continue;
        }
        for j in 0..n {
            let _ = it.next().unwrap();
        }
    }

    println!("{} {}", 2 * (n - 1), 2 * (n - 1) - 1);
}