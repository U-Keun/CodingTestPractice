use std::io;

fn main() {
    let num: usize = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        buf.trim().parse().unwrap()
    };

    for i in 1..=num {
        print!("{}", " ".repeat(num - i));
        
        if i == 1 {
            println!("*");
        } else if i < num {
            println!("*{}*", " ".repeat(2 * i - 3));
        } else {
            println!("{}", "*".repeat(2 * i - 1));
        }
    }
}