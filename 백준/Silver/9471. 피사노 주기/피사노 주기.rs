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

    let p = next!(it, usize);
    for _ in 0..p {
        let n = next!(it, usize);
        let m = next!(it, u64);

        let (mut prev, mut cur) = (0u64, 1u64);
        for t in 1..=m * m {
            let next = (prev + cur) % m;
            prev = cur;
            cur = next;
            if prev == 0 && cur == 1 {
                println!("{n} {t}");
                break;
            }
        }
    }
}