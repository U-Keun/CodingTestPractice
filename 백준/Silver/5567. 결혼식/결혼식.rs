use std::io::{ self, Read };
use std::collections::VecDeque;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n: usize = next!(it, usize);
    let m: usize = next!(it, usize);

    let mut g = vec![Vec::<usize>::new(); n + 1];
    for _ in 0..m {
        let a: usize = next!(it, usize);
        let b: usize = next!(it, usize);
        g[a].push(b);
        g[b].push(a);
    }

    let mut dist = vec![-1_i32; n + 1];
    let mut q = VecDeque::new();
    dist[1] = 0;
    q.push_back(1);

    while let Some(u) = q.pop_front() {
        if dist[u] == 2 {
            continue;
        }
        for &v in &g[u] {
            if dist[v] == -1 {
                dist[v] = dist[u] + 1;
                q.push_back(v);
            }
        }
    }

    let answer = dist
        .iter()
        .skip(2)
        .filter(|&&d| d == 1 || d == 2)
        .count();

    println!("{answer}");
}