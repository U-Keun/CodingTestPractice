use std::io::{ self, Read };
use std::collections::HashMap;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn compress(arr: &[i64]) -> Vec<usize> {
    let mut order: Vec<(i64, usize)> = arr.iter().cloned().zip(0..arr.len()).collect();
    order.sort_by_key(|&(v, _)| v);
    let mut rank = vec![0usize; arr.len()];
    let mut cur = 0usize;
    for i in 0..order.len() {
        if i > 0 && order[i].0 != order[i - 1].0 {
            cur += 1;
        }
        rank[order[i].1] = cur;
    }
    rank
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let m = next!(it, usize);
    let n = next!(it, usize);

    let mut freq: HashMap<Vec<usize>, usize> = HashMap::new();

    for _ in 0..m {
        let arr: Vec<i64> = (0..n).map(|_| next!(it, i64)).collect();
        let key = compress(&arr);
        *freq.entry(key).or_insert(0) += 1;
    }

    let answer: usize = freq.values().map(|&k| k * (k - 1) / 2).sum();
    println!("{answer}");
}