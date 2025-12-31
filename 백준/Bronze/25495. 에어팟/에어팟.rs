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
    let mut prev = 0usize;
    let mut prev_e = 0usize;
    let mut ans = 0usize;

    for _ in 0..n {
        let v = next!(it, usize);
        if prev != v {
            prev_e = 2;
            ans += 2;
            prev = v;
        } else {
            prev_e *= 2;
            ans += prev_e;
        }
        if ans >= 100 { 
            prev_e = 1;
            ans = 0; 
        }
    }
    println!("{ans}");
}