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
    let mut t = vec![0usize; n];
    for i in 0..n { t[i] = next!(it, usize); }
    t.sort_by(|a, b| b.cmp(a));

    let mut ans = 0usize;
    for i in 0..n {
        ans = ans.max(i + t[i] + 2);
    }
    println!("{ans}");
}