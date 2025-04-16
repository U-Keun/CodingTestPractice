use std::io;

fn main() {
    let mut count = 0u32;
    let mut answer = 0u32;
    for _ in 0..4 {
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();

        let parsing: Vec<u32> = input
            .split_whitespace()
            .map(|x| x.parse().unwrap())
            .collect();

        let (_out, _in) = (parsing[0], parsing[1]);

        count -= _out;
        count += _in;

        if answer < count {
            answer = count;
        }
    }

    println!("{}", answer);
}