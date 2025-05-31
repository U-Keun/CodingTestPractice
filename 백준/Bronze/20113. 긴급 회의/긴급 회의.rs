use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let n: usize = lines.next().unwrap().unwrap()
        .trim().parse().unwrap();

    let mut votes = vec![0; n + 1];

    lines.next().unwrap().unwrap()
        .split_whitespace()
        .map(|s| s.parse::<usize>().unwrap())
        .for_each(|p| votes[p] += 1);   

    let winners: Vec<_> = votes.iter().enumerate().skip(1)
        .filter(|&(_, &cnt)| cnt == *votes.iter().skip(1).max().unwrap())
        .map(|(idx, _)| idx)
        .collect();

    if winners.len() == 1 {
        println!("{}", winners[0]);
    } else {
        println!("skipped");
    }
}