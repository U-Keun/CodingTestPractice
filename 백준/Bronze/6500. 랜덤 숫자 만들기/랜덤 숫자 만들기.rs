use std::io::{ self, Read };
use std::collections::HashSet;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let mut first = next!(it, u32);
    while first != 0 {
        let mut seen = HashSet::with_capacity(10000);
        let mut x = first;
        let mut count = 0usize;
        loop {
            if !seen.insert(x) { break; }
            count += 1;
            x = ((x * x) / 100) % 10000;
        }
        
        println!("{count}");

        first = next!(it, u32);
    }
}