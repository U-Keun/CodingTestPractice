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

    let x = next!(it, isize);
    let y = next!(it, isize);
    let mut p = next!(it, isize);
    let a = next!(it, isize);
    let b = next!(it, isize);

    p += b * (y - 1);
    let mut ans = 0isize;

    for _ in 0..x {
        ans += p;
        p -= a;
    }

    println!("{ans}");
}