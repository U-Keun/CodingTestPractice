use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn digit_sum(mut n: u32) -> u32 {
    let mut s = 0;
    while n > 0 {
        s += n % 10;
        n /= 10;
    }
    s
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n: usize = next!(it, usize);

    let mut freq = [0u32; 1000];
    for _ in 0..n {
        let x: usize = next!(it, usize);
        freq[x] += 1;
    }

    let mut best = 0u32;
    for a in 1..=999 {
        if freq[a] == 0 { continue; }

        for b in a..=999 {
            if freq[b] == 0 { continue; }
            if a == b && freq[a] < 2 { continue; }

            let s = digit_sum((a as u32) * (b as u32));
            if s > best { best = s; }
        }
    }

    println!("{best}");
}