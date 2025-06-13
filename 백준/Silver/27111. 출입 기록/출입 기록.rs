macro_rules! read_line {
    ($lines:expr) => {
        $lines.next().unwrap().unwrap()
    };
}

use std::io::{ self, BufRead };
use std::collections::HashMap;

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let num: usize = read_line!(lines).trim().parse().unwrap();

    let mut map: HashMap<u32, u8> = HashMap::new();
    let mut answer = 0;
    for _ in 0..num {
        let (a, b): (u32, u8) = {
            let line = read_line!(lines);
            let mut iter = line.split_whitespace();
            (
                iter.next().unwrap().parse::<u32>().unwrap(),
                iter.next().unwrap().parse::<u8>().unwrap()
            )
        };

        if let Some(&val) = map.get(&a) {
            if val == b {
                answer += 1;
            }
        } else {
            if b == 0 {
                answer += 1;
            }
        }
        map.insert(a, b);
    }

    for (_, &v) in &map {
        if v == 1 {
            answer += 1;
        }
    }

    println!("{}", answer);
}