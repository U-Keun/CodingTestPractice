use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();

    let mut iter = buf.trim().split_whitespace()
        .map(|s| s.parse::<isize>().unwrap());

    let a = iter.next().unwrap();
    let b = iter.next().unwrap();
    let c = iter.next().unwrap();

    if a + b == c {
        println!("{}+{}={}", a, b, c);
    } else if a - b == c {
        println!("{}-{}={}", a, b, c);
    } else if a * b == c {
        println!("{}*{}={}", a, b, c);
    } else if a / b == c {
        println!("{}/{}={}", a, b, c);
    } else if a == b + c {
        println!("{}={}+{}", a, b, c);
    } else if a == b - c {
        println!("{}={}-{}", a, b, c);
    } else if a == b * c {
        println!("{}={}*{}", a, b, c);
    } else if a == b / c {
        println!("{}={}/{}", a, b, c);
    }
}