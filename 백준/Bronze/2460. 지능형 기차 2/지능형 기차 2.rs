use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let reader = io::BufReader::new(stdin.lock());

    let mut count = 0u32;
    let mut max = 0u32;
    for line in reader.lines() {
        let line = line.unwrap();
        let mut iter = line.split_whitespace();

        let _out: u32 = iter.next().expect("out")
            .parse().expect("parse error");
        let _in: u32 = iter.next().expect("in")
            .parse().expect("parse error");

        count -= _out;
        count = (count + _in).min(10000);

        max = max.max(count);
    }

    println!("{}", max);
}