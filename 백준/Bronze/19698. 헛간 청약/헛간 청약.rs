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
    let w = next!(it, usize);
    let h = next!(it, usize);
    let l = next!(it, usize);

    let answer = n.min((w / l) * (h / l));
    println!("{answer}");
}