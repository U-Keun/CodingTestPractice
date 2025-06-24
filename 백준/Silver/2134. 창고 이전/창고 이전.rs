use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let line = lines.next().unwrap().unwrap();
    let mut iter = line
        .split_whitespace();

    let n: usize = iter.next().unwrap().parse().unwrap();
    let m: usize = iter.next().unwrap().parse().unwrap();
    let _k = iter.next().unwrap();

    let mut old = Vec::with_capacity(n);
    for _ in 0..n {
        let line = lines.next().unwrap().unwrap();
        let x = line.trim().parse::<usize>().unwrap();
        old.push(x);
    }

    let mut new = Vec::with_capacity(m);
    for _ in 0..m {
        let line = lines.next().unwrap().unwrap();
        let x = line.trim().parse::<usize>().unwrap();
        new.push(x);
    }

    let mut old_idx = 0usize;
    let mut new_idx = 0usize;
    let mut count = 0usize;
    let mut cost = 0usize;
    loop {
        if old_idx == n || new_idx == m {
            break;
        }

        if old[old_idx] > 0 && new[new_idx] > 0 {
            count += 1;
            cost += old_idx + new_idx + 2;
            old[old_idx] -= 1;
            new[new_idx] -= 1;
        } else {
            if old[old_idx] == 0 {
                old_idx += 1;
            }

            if new[new_idx] == 0 {
                new_idx += 1;
            }
        }
    }

    println!("{} {}", count, cost);
}