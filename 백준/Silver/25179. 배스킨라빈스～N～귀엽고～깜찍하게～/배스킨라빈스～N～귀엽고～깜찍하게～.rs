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

    let n = next!(it, u64);
    let m = next!(it, u64);

    if (n - 1) % (m + 1) == 0 {
        println!("Can't win");
    } else {
        println!("Can win");
    }
}