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
    let m = next!(it, usize);

    let (mut l, mut r, mut b, mut t) = (0usize, n - 1, 0usize, m - 1);
    let (mut x, mut y) = (0usize, 0usize);

    let mut rem = n * m - 1;

    loop {
        let len = r - x;
        if rem <= len { x += rem; break; }
        x = r; rem -= len; b += 1;

        let len = t - y;
        if rem <= len { y += rem; break; }
        y = t; rem -= len; r -= 1;

        let len = x - l;
        if rem <= len { x -= rem; break; }
        x = l; rem -= len; t -= 1;

        let len = y - b;
        if rem <= len { y -= rem; break; }
        y = b; rem -= len; l += 1;
    }

    println!("{} {}", x, y);
}