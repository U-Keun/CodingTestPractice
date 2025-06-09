use std::io;

fn main() {
    let num: u32 = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        buf.trim().parse().unwrap()
    };

    for i in 1..=num {
        println!("Hello World, Judge {}!", i);
    }
}