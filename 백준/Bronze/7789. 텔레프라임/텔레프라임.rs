use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn is_prime(num: u32) -> bool {
    if num == 2 {
        return true;
    }
    let mut d = 2u32;
    while d * d < num {
        if num % d == 0 { return false; }
        d += 1;
    }
    true
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let num: u32 = next!(it, u32);
    let k: u32 = next!(it, u32);

    if !is_prime(num) {
        println!("No");
        return;
    }

    let new_num = k * 1000000 + num;

    if !is_prime(new_num) {
        println!("No");
    } else { println!("Yes"); }
}