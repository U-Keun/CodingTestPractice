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
    let ink: Vec<u64> = (0..n).map(|_| next!(it, u64)).collect();
    let b: Vec<u64> = (0..n).map(|_| next!(it, u64)).collect();

    let mut answer = Vec::with_capacity(n);
    for i in 0..n {
        let idx = b.partition_point(|&x| x <= ink[i]);
        let cnt = idx.saturating_sub(i + 1);
        answer.push(cnt);
    }
    
    println!("{}", answer.iter().map(ToString::to_string)
        .collect::<Vec<_>>().join(" "));
}