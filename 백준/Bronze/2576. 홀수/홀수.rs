use std::io::{ self, BufRead };

fn main() {
    let stdin = io::stdin();
    let mut sum = 0u32;
    let mut min = 100u32;
    for line in stdin.lock().lines().take(7) {
        let num = line.unwrap()
            .trim()
            .parse()
            .unwrap();

        if num % 2 == 1 {
            sum += num;
            min = min.min(num);
        }
    }

    if sum == 0 {
        println!("-1");
    } else {
        println!("{}", sum);
        println!("{}", min);
    }
}