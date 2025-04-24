use std::io;

fn digits_upto(n: u64) -> u64 {
    let mut ans = 0;
    let mut start = 1u64;
    let mut k = 1u64;

    while n >= start * 10 {
        ans += 9 * start * k;
        start *= 10;
        k += 1;
    }
    ans += (n - start + 1) * k;
    ans
}

fn main() {
    let n: u64 = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).expect("input error");
        buf.trim().parse().expect("Not an integer")
    };
    println!("{}", digits_upto(n));
}