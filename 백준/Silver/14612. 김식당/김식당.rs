use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let nums = lines.next().unwrap().unwrap();
    let mut iter = nums.trim().split_whitespace()
        .map(|s| s.parse::<usize>().unwrap());

    let n = iter.next().unwrap();
    let m = iter.next().unwrap();
    
    let mut times = vec![1001; m + 1];
    let mut indices: Vec<usize> = Vec::new();
    
    for _ in 0..n {
        let query = lines.next().unwrap().unwrap();
        let mut iter = query.trim().split_whitespace();
        match iter.next() {
            Some("order") => {
                let i: usize = iter.next().unwrap().parse().unwrap();
                let t: usize = iter.next().unwrap().parse().unwrap();
                times[i] = t;
                indices.push(i);
            }
            Some("sort") => {
                indices.retain(|&i| times[i] != 1001);
                indices.sort_by_key(|&i| (times[i], i));
            }
            Some("complete") => {
                let i: usize = iter.next().unwrap().parse().unwrap();
                times[i] = 1001;
                indices.retain(|&i| times[i] != 1001);
            }
            _ => {}
        }

        if indices.is_empty() {
            println!("sleep");
        } else { 
            let line = indices.iter()
                .map(|i| i.to_string())
                .collect::<Vec<_>>()
                .join(" ");
            println!("{}", line);
        }
    }
}