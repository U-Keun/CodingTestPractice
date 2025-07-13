use std::io::{ self, Read };
use std::collections::HashMap;

macro_rules! next {
    ($iter: expr, $t: ty) => {
        $iter.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut input = String:: new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut it = input.split_whitespace();

    let x: usize = next!(it, usize);
    let _k: usize = next!(it, usize);

    let mut freq: HashMap<u32, u64> = HashMap::new();

    for _ in 0..x {
        let a: u32 = next!(it, u32);
        *freq.entry(a).or_insert(0) += 1;
    }

    let mut answer = 0u64;

    for _ in 0..x {
        let a: u32 = next!(it, u32);
        answer += x as u64 - freq.get(&a).copied().unwrap_or(0);
    }
    
    println!("{}", answer);
}