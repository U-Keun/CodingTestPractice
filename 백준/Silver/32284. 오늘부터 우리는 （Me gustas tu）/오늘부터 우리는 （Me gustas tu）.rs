use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let (n, m) = (next!(it, usize), next!(it, usize));
    let (a, b) = (next!(it, usize), next!(it, usize));

    for i in 0..n {
        for j in 0..m {
            if i == a {
                if j < b { print!("E"); }
                else { print!("W"); }
            } else if i < a { print!("S"); } 
            else { print!("N"); }
        }
        println!();
    }
}