use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn rec(m: &[Vec<u32>]) -> (u32, usize) {
    let n = m.len() as usize;
    let mut cnt = 0;
    let best = inner_rec(m, n, n, &mut cnt);
    (best, cnt)
}

fn inner_rec(m: &[Vec<u32>], i: usize, j: usize, cnt: &mut usize) -> u32 {
    if i == 0 || j == 0 {
        *cnt += 1;
        return 0;
    }
    let mij = m[i - 1][j - 1];
    let up = inner_rec(m, i - 1, j, cnt);
    let left = inner_rec(m, i, j - 1, cnt);
    mij + up.max(left)
}

fn dp(m: &[Vec<u32>]) -> (u32, usize) {
    let n = m.len();
    let mut d = vec![vec![0; n + 1]; n + 1];

    let mut cnt = 0;
    for i in 1..=n {
        for j in 1..=n {
            d[i][j] = m[i - 1][j - 1] + d[i - 1][j].max(d[i][j - 1]);
            cnt += 1;
        }
    }
    (d[n][n], cnt)
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n: usize = next!(it, usize);
    let matrix: Vec<Vec<u32>> = (0..n)
        .map(|_| {
            (0..n)
                .map(|_| next!(it, u32))
                .collect()
        })
        .collect();

    let (_, code1) = rec(&matrix);
    let (_, code2) = dp(&matrix);
    println!("{code1} {code2}");
}