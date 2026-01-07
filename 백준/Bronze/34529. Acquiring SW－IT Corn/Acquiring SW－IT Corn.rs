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

    let (x, y, z): (usize, usize, usize) = 
                    (next!(it, usize), next!(it, usize), next!(it, usize));
    let (u, v, w): (usize, usize, usize) = 
                    (next!(it, usize), next!(it, usize), next!(it, usize));

    let ans = x * u / 100 + y * v / 50 + z * w / 20;
    println!("{ans}");
}