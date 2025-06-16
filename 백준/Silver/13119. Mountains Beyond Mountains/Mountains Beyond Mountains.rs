use std::io::{ self, Read, Write };

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut iter = buf.split_whitespace();

    let n: usize = iter.next().unwrap().parse().unwrap();
    let m: usize = iter.next().unwrap().parse().unwrap();
    let x: usize = iter.next().unwrap().parse().unwrap();

    let mut heights = Vec::with_capacity(m);
    for _ in 0..m {
        heights.push(iter.next().unwrap().parse::<usize>().unwrap());
    }

    let mut out = String::with_capacity(n * (m + 1));
    for r in 0..n {
        let mut line = String::with_capacity(m);
        for c in 0..m {
            let h = heights[c];
            let ch = if r == n - x {
                if h >= x { '*' } else { '-' }
            } else if r > n - x {
                let j = n - r;
                if j <= h {
                    '#'
                } else if c % 3 == 2 {
                    '|'
                } else {
                    '.'
                }
            } else {
                let j = n - r;
                if j <= h {
                    '#'
                } else {
                    '.'
                }
            };
            line.push(ch);
        }
        out.push_str(&line);
        out.push('\n');
    }

    io::stdout().write_all(out.as_bytes()).unwrap();
}