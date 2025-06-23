use std::io;
use std::collections::HashMap;

fn dfs(n: usize, start: u64, product: u64, map: &mut HashMap<u64, usize>) {
    if n == 0 {
        *map.entry(product).or_insert(0) += 1;
        return;
    }
    for i in start..=9 {
        dfs(n - 1, i, product * i, map);
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).expect("Failed to read a line");

    let num: usize = buf.trim().parse().expect("Failed to parse");

    let mut map = HashMap::new();
    dfs(num, 1, 1, &mut map);

    println!("{}", map.len());
}