use std::io::{self, Read};

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
    let mut it = input.split_whitespace().map(|s| s.parse::<usize>().unwrap());

    let n = it.next().unwrap();
    let c = it.next().unwrap();

    let mut factors: Vec<usize> = (0..n)
        .filter_map(|_| {
            let a = it.next().unwrap();
            (a <= c).then_some(a)
        })
        .collect();
    factors.sort_unstable();
    factors.dedup();

    let mut marked = vec![false; c + 1]; // 인덱스 0은 사용 안 함
    for &a in &factors {
        for m in (a..=c).step_by(a) {
            marked[m] = true;
        }
    }

    let ans = marked.iter().skip(1).filter(|&&b| b).count();
    println!("{ans}");
}