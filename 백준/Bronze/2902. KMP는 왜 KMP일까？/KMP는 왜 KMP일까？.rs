use std::io;

fn main() {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();

    let initials: String = buf.trim()
        .split('-')
        .filter_map(|name| name.chars().next())
        .collect();

    println!("{}", initials);
}