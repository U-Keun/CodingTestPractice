use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let _n: usize = lines
        .next().unwrap().unwrap()
        .trim().parse().unwrap();

    let s = lines.next().unwrap().unwrap();

    let mut chars = s.chars();
    let mut prev_char = match chars.next() {
        Some(c) => c,
        None => {
            println!("0");
            return;
        }
    };

    let mut prev_count = 0;
    let mut curr_count = 1;
    let mut answer = 0;

    for c in chars {
        if c == prev_char {
            curr_count += 1;
        } else {
            answer = answer.max(prev_count.min(curr_count) * 2);
            prev_char = c;
            prev_count = curr_count;
            curr_count = 1;
        }
    }
    answer = answer.max(prev_count.min(curr_count) * 2);

    println!("{}", answer);
}