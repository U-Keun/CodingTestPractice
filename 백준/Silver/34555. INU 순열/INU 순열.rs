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
    let mut l = n / 2;
    let mut r = l + 1;

    while l > 0 { print!("{} {} ", r, l); r += 1; l -= 1; }
    if r == n { print!("{r}"); }
    println!("");
}