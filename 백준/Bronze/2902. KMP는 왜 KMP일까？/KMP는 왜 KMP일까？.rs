use std::io;

fn main() {
    let input = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        buf.trim().to_string()
    };

    let names = input.split('-');

    let initials: String = names
        .filter_map(|name| name.chars().next())
        .collect();

    println!("{}", initials);
}