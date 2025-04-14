use std::io;

fn main() {
    let results = ["D", "C", "B", "A", "E"];

    for _ in 0..3 {
        let mut input = String::new();
        io::stdin().read_line(&mut input).unwrap();

        let count = input.trim().chars().filter(|&c| c == '1').count();

        let output = results.get(count).unwrap_or(&"?");
        println!("{}", output);
    }
}