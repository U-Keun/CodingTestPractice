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

    let n: usize = next!(it, usize);

    let row1 = {
        let s = "* ".repeat(n);
        s.trim_end().to_string()
    };
    let row2 = format!(" {}", row1);

    for i in 0..n {
        if i % 2 == 0 {
            println!("{row1}");
        } else {
            println!("{row2}");
        }
    }
}