use std::io;

fn main() {
    let n: u32 = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        buf.trim().parse().unwrap()
    };

    let message = "WelcomeToSMUPC";
    let idx = ((n - 1) % 14) as usize;

    let chars: Vec<char> = message.chars().collect();
    println!("{}", chars[idx]);
}