use std::io;
use std::collections::BTreeMap;

fn main() {
    let num = read_num();

    let mut freq: BTreeMap<char, usize> = BTreeMap::new();

    for _ in 0..num {
        let name = read_name();

        if let Some(ch) = name.chars().next() {
            let initial = ch.to_ascii_lowercase();
            *freq.entry(initial).or_insert(0) += 1;
        }
    }

    let frequent: Vec<char> = freq
        .into_iter()
        .filter(|&(_, count)| count >= 5)
        .map(|(initial, _)| initial)
        .collect();

    if frequent.is_empty() {
        println!("PREDAJA");
    } else {
        for initial in frequent {
            print!("{}", initial);
        }
        println!();
    }   
}

fn read_num() -> u32 {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    buf.trim().parse::<u32>().unwrap()
}

fn read_name() -> String {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    buf.trim().to_string()
}