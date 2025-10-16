use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn dfs(rem: usize, cur: usize, s: usize, visit: &mut [Vec<bool>]) -> bool {
    if cur > s { return false; }
    if rem == 0 { return cur == s; }
    if visit[rem][cur] { return false; }
    visit[rem][cur] = true;

    for i in 1..=rem {
        let add = (i * (i + 1)) / 2;
        if dfs(rem - i, cur + add, s, visit) { return true; }
    }
    false
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let (n, s) = (next!(it, usize), next!(it, usize));
    if n <= 2 {
        println!("0");
        return;
    }

    let mut visit = vec![vec![false; s + 1]; n - 1];
    let ok = dfs(n - 2, 0, s, &mut visit);
    println!("{}", if ok { 1 } else { 0 });
}