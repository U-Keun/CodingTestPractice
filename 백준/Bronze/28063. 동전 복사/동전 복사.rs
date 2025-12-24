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
    let x = next!(it, usize);
    let y = next!(it, usize);

    if n == 1 { println!("0"); return; }

    if x == 1 || x == n {
        if y == 1 || y == n { println!("2"); }
        else { println!("3"); }
    } else {
        if y == 1 || y == n { println!("3"); }
        else { println!("4"); }
    }
}