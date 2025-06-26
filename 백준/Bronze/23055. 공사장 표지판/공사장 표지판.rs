use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    let n: usize = buf.trim().parse().unwrap();

    for r in 0..n {
        let mut row = String::with_capacity(n);
        for c in 0..n {
            let ch = if r == 0 || r + 1 == n
                || c == 0 || c + 1 == n
                || c == r || c == n - 1 - r {
                '*'
            } else {
            ' '
            };
            row.push(ch);
        }
        println!("{}", row);
    }
}