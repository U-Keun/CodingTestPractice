use std::{
    io::{ self, Read },
    collections::HashMap,
};

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf
        .split_whitespace()
        .map(|w| w.parse::<usize>().unwrap());

    let num = it.next().unwrap();
    let mut map = HashMap::new();

    let answer = it
        .take(num)
        .fold(0, |acc, h| {
            let c = map.entry(h).or_insert(0);
            if *c >= 2 {
                *c += 1;
                acc
            } else {
                *c += 1;
                acc + 1
            }
        });

    println!("{}", answer);
}