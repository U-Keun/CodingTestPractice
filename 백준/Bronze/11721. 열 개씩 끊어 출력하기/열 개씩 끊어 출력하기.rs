use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let input = input.trim();

    let mut i = 0;
    let l = input.len();

    while i < l {
        let end = (i + 10).min(l);
        println!("{}", &input[i..end]);
        i += 10;
    }
}