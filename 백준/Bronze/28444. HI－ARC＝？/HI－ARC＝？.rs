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

    let h = next!(it, i32);
    let i = next!(it, i32);
    let a = next!(it, i32);
    let r = next!(it, i32);
    let c = next!(it, i32);

    println!("{}", h * i - a * r * c);
}