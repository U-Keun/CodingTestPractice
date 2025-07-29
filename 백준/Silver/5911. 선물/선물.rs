use std::io::{ self, Read };

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
    let b: u64 = next!(it, u64);

    let pairs: Vec<(u64, u64)> = (0..n)
        .map(|_| { (next!(it, u64), next!(it, u64)) })
        .collect();
    
    let mut best = 0;

    for i in 0..n {
        let mut cost: Vec<u64> = pairs.iter().enumerate().map(|(k, &(p, s))| {
            if k == i { p / 2 + s } else { p + s }
        }).collect();

        cost.sort_unstable();

        let mut sum = 0;
        let mut cnt = 0;
        
        for c in cost {
            sum += c;
            if sum > b { break; }
            cnt += 1;
        }
        best = best.max(cnt);
    }
    
    println!("{best}");
}