use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let answer = match (input.find('@'), input.find('#'), input.find('!')) {
        (Some(r), Some(b), Some(d))
            if (b > r && b < d) || (b > d && b < r) => {
                (r.abs_diff(d) as isize) - 1
            }
        _ => -1,
    };

    println!("{}", answer);
}