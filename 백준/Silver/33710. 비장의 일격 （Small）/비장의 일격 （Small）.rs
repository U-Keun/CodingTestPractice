use std::io;

fn main() {
    let mut line = String::new();

    io::stdin().read_line(&mut line).unwrap();
    let mut it = line.trim().split_whitespace();
    let n: usize = it.next().unwrap().parse().unwrap();
    let k: usize = it.next().unwrap().parse().unwrap();

    let mut s_line = String::new();
    io::stdin().read_line(&mut s_line).unwrap();
    let s: Vec<char> = s_line.trim().chars().collect();

    let ans = brute(&s, k);
    println!("{}", ans);
}

fn brute(s: &[char], k: usize) -> usize {
    if k == 0 {
        return s.len();
    }

    let n = s.len();
    let mut best = n;

    for i in 0..n {
        for j in (i + 1)..n {
            if s[i] == s[j] && s[i] != 'X'  {
                let mut t = Vec::with_capacity(n - (j - i + 1));
                t.extend_from_slice(&s[..i]);
                t.extend_from_slice(&s[j + 1..]);

                best = best.min(brute(&t, k - 1));
            }
        }
    }
    best.min(n)
}