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
    for i in 0..n {
        let leading_spaces = n - 1 - i;
        let interior_spaces = if i == 0 { 0 } else { 2 * i - 1 };

        let mut line = String::with_capacity(leading_spaces + 1 + interior_spaces + 1);
        line.extend(std::iter::repeat(' ').take(leading_spaces));
        line.push('*');
        if interior_spaces > 0 {
            line.extend(std::iter::repeat(' ').take(interior_spaces));
            line.push('*');
        }
        println!("{line}");
    }

}