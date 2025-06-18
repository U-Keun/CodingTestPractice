use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    let mut it = buf.trim().split_whitespace();
    let n: usize = it.next().unwrap().parse().unwrap();
    let x: usize = it.next().unwrap().parse().unwrap();

    let mut nums = String::new();
    io::stdin().read_line(&mut nums).unwrap();
    let s: Vec<usize> = nums.trim()
        .split_whitespace()
        .map(|k| k.parse::<usize>().unwrap())
        .collect();

    let answer = s
        .iter()
        .enumerate()
        .map(|(i, &si)| {
            let init = x + i;
            let v = if init > si {
                init
            } else {
                let extra = (si - init) / n + 1;
                init + extra * n
            };
            (i + 1, v)
        })
        .min_by_key(|&(_, v)| v)
        .unwrap()
        .0;

    println!("{}", answer);
}