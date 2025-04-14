use std::io;

fn main() {
    for _i in 0..3 {
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();

        let input = input.trim();

        let count = input.chars().filter(|&c| c == '1').count();

        match count {
            0 => println!("D"),
            1 => println!("C"),
            2 => println!("B"),
            3 => println!("A"),
            4 => println!("E"),
            _ => println!("?"),
        }
    }
}