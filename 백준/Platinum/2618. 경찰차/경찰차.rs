use std::io::{ self, Read };
use std::cmp::max;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn dist(a: (i32, i32), b: (i32, i32)) -> i32 {
    (a.0 - b.0).abs() + (a.1 - b.1).abs()
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, i32);
    let w = next!(it, usize);

    let mut events = vec![(0i32, 0i32); w + 1];
    for k in 1..=w {
        let x = next!(it, i32);
        let y = next!(it, i32);
        events[k] = (x, y);
    }

    const INF: i32 = i32::MAX;

    let mut dp = vec![vec![INF; w + 1]; w + 1];
    let mut parent = vec![vec![(0usize, 0usize); w + 1]; w + 1];
    let mut choice = vec![vec![0u8; w + 1]; w + 1];

    dp[0][0] = 0;

    for k in 0..w {
        let next = k + 1;
        for i in 0..=k {
            let j = k;
            let cur = dp[i][j];
            if cur == INF { continue; }

            let first = if i == 0 { (1, 1) } else { events[i] };
            let cost = cur + dist(first, events[next]);
            if cost < dp[next][j] {
                dp[next][j] = cost;
                parent[next][j] = (i, j);
                choice[next][j] = 1;
            }

            let second = if j == 0 { (n, n) } else { events[j] };
            let cost = cur + dist(second, events[next]);
            if cost < dp[i][next] {
                dp[i][next] = cost;
                parent[i][next] = (i, j);
                choice[i][next] = 2;
            }
        }

        if k > 0 {
            let i = k;
            for j in 0..k {
                let cur = dp[i][j];
                if cur == INF { continue; }
                let first = if i == 0 { (1, 1) } else { events[i] };
                let cost = cur + dist(first, events[next]);
                if cost < dp[next][j] {
                    dp[next][j] = cost;
                    parent[next][j] = (i, j);
                    choice[next][j] = 1;
                }

                let second = if j == 0 { (n, n) } else { events[j] };
                let cost = cur + dist(second, events[next]);
                if cost < dp[i][next] {
                    dp[i][next] = cost;
                    parent[i][next] = (i, j);
                    choice[i][next] = 2;
                }

            }
        }
    }

    let mut best = INF;
    let mut end = (0usize, 0usize);
    for i in 0..=w {
        if dp[i][w] < best {
            best = dp[i][w];
            end = (i, w);
        }
    }
    for j in 0..=w {
        if dp[w][j] < best {
            best = dp[w][j];
            end = (w, j);
        }
    }

    println!("{best}");

    let mut who = vec![0u8; w + 1];
    let (mut i, mut j) = end;
    while max(i, j) > 0 {
        let k = max(i, j);
        who[k] = choice[i][j];
        let (pi, pj) = parent[i][j];
        i = pi; j = pj;
    }

    for k in 1..=w {
        println!("{}", who[k]);
    }

}