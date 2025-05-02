use std::io;

fn main() {
    let mut input = String::new();

    loop {
        input.clear();
        match io::stdin().read_line(&mut input) {
            Ok(0) => {
                break;
            },
            Ok(_) => {
                let line = input.trim();
                if line == "#" { break; }
                let vowels = line.bytes().fold(0, |acc, b| {
                    match b.to_ascii_lowercase() {
                        b'a' | b'e' | b'i' | b'o' | b'u' => acc + 1,
                        _ => acc,
                    }
                });

                println!("{}", vowels);
            },
            Err(err) => {
                eprintln!("input error: {}", err);
                break;
            }
        }
    }
}