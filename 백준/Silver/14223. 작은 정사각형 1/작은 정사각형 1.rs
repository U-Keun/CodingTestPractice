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

    let n = next!(it, usize);
    let coord: Vec<(i64, i64)> = (0..n)
        .map(|_| (next!(it, i64), next!(it, i64)))
        .collect();

    let mut answer: Option<u64> = None;
    for i in 0..n {
        for j in 0..n {
            if i == j { continue; }

            let mut setup = false;
            let (mut left, mut right, mut below, mut above) = (0i64, 0, 0, 0);
            for k in 0..n {
                if k == i || k == j { continue; }
                let (x, y) = coord[k];
                
                if !setup {
                    setup = true;
                    left = x;
                    right = x;
                    below = y;
                    above = y;
                } else {
                    if x < left { left = x; }
                    if x > right { right = x; }
                    if y < below { below = y; }
                    if y > above { above = y; }

                }
            }
            let w = (right - left + 2).max(above - below + 2) as u64;

            if answer.is_none() { answer = Some(w * w); }
            else { answer =  answer.min(Some(w * w)); }
        }
    }
    println!("{}", answer.unwrap());
}