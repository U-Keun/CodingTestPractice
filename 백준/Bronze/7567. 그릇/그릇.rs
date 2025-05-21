use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    
    let mut prev = '.';
    let mut answer = 0u32;
    for ch in buf.trim().chars() {
        if ch == prev {
            answer += 5;
        } else {
            answer += 10;
        }
        prev = ch;
    }

    println!("{}", answer);
}