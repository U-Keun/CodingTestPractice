use std::io::{ self, BufRead };

fn color_to_val(color: &str) -> u32 {
    match color {
        "black" => 0,
        "brown" => 1,
        "red" => 2,
        "orange" => 3,
        "yellow" => 4,
        "green" => 5,
        "blue" => 6,
        "violet" => 7,
        "grey" => 8,
        "white" => 9,
        _ => panic!("unknown color: {}", color),
    }
}

fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();

    let values: Vec<u32> = (0..3)
        .map(|_| lines.next().unwrap().unwrap())
        .map(|s| s.trim().to_lowercase())
        .map(|s| color_to_val(&s))
        .collect();

    let head = values[0] * 10 + values[1];
    let res = (head as u64) * 10u64.pow(values[2]);
    println!("{}", res);
}