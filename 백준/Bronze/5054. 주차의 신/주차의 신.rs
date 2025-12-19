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

    let t = next!(it, usize);
    for _ in 0..t {
        let n = next!(it, usize);
        let mut l = 100usize;
        let mut r = 0usize;
        for _ in 0..n {
            let tmp = next!(it, usize);
            l = tmp.min(l);
            r = tmp.max(r);
        }
        println!("{}", (r - l) * 2);
    }
}