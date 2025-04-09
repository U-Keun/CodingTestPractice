use std::io;

fn get_days_in_months(month: u32) -> u32 {
    match month {
        1 | 3 | 5 | 7 | 8 | 10 | 12 => 31,
        4 | 6 | 9 | 11 => 30,
        2 => 28,
        _ => 0,
    }
}

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let date: Vec<u32> = input
        .split_whitespace()
        .map(|x| x.parse().unwrap())
        .collect();

    let (month, day) = (date[0], date[1]);

    let mut total = 0;
    for i in 1..month {
        total += get_days_in_months(i);
    }
    total += day;

    let weekday = (total - 1) % 7;

    let weekday_str = match weekday {
        0 => "MON", 1 => "TUE", 2 => "WED",
        3 => "THU", 4 => "FRI", 5 => "SAT",
        6 => "SUN", _ => "???"
    };

    println!("{weekday_str}");
}