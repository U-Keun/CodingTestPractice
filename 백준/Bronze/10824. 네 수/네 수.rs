use std::io;

fn main() {
    let nums: Vec<String> = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        buf.trim().split_whitespace()
            .map(|s| s.to_string()).collect()
    };

    let a: u64 = (nums[0].to_string() + &nums[1]).parse().unwrap();
    let b: u64 = (nums[2].to_string() + &nums[3]).parse().unwrap();
    println!("{}", a + b);
}