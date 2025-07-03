use std::io::{ self, Read };

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();

    for line in buf.lines() {
        let mut iter = line.split_whitespace()
            .map(|s| s.parse::<isize>().unwrap());

        let n = iter.next().unwrap() as usize;
        if n < 2 {
            println!("Jolly");
            continue;
        };

        let mut prev = iter.next().unwrap();

        let mut checked = vec![false; n];

        let mut is_jolly: bool = true;
        for _ in 1..n {
            let cur = iter.next().unwrap();
            let d = (cur - prev).abs() as usize;

            if d == 0 || d >= n || checked[d] {
                is_jolly = false;
                break;
            }
            checked[d] = true;
            prev = cur;
        }
        
        if is_jolly {
            println!("Jolly");
        } else {
            println!("Not jolly");
        }
    }
}