use std::io::{ self, BufRead, Write };

fn main() {
    let stdin = io::stdin();
    let mut input = stdin.lock().lines();
    
    let n: usize = input
        .next().unwrap().unwrap()
        .trim().parse().unwrap();

    let choice = vec!["swimming", "bowling", "soccer"];
    let mut out = io::stdout().lock();

    for msg in choice.iter().cycle().take(n) {
        write!(out, "{} ", msg).unwrap();
    }
    writeln!(out).unwrap();
    out.flush().unwrap();

    let notice = input.next().unwrap().unwrap();
    let mut iter = notice.split_whitespace();

    for i in 0..n {
        let s = iter.next().unwrap();
        let pick = choice
            .iter()
            .find(|&&c| c != s && c != choice[i % 3])
            .unwrap();
        write!(out, "{}", pick).unwrap();
        if i + 1 < n {
            write!(out, " ").unwrap();
        }
    }
    writeln!(out).unwrap();
    out.flush().unwrap();
}