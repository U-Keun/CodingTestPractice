use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn is_prime(n: u32) -> bool {
    if n <= 1 { return false; }
    if n == 2 { return true; }
    if n % 2 == 0 { return false; }

    let mut i = 3;
    while i * i <= n {
        if n % i == 0 { return false; }
        i += 2;
    }
    true
}

fn num_has(mut num: u32, d: u32) -> bool {
    while num > 0 {
        let r = num % 10;
        if r == d { return true; }
        num /= 10;
    }
    false
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let a: u32 = next!(it, u32);    
    let b: u32 = next!(it, u32);    
    let d: u32 = next!(it, u32);    

    let mut count = 0u32;
    for i in a..=b {
        if is_prime(i as u32) && num_has(i as u32, d) {
            count += 1
        }
    }

    println!("{}", count);
}