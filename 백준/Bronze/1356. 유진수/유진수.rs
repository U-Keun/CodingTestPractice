use std::io::{ self, Read };

fn main() -> io::Result<()> {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input)?;
    let num = input.trim();

    let digits: Vec<u64> = num
        .chars()
        .map(|c| c.to_digit(10).expect("Not a number") as u64)
        .collect();

    if digits.len() == 1 {
        println!("NO");
        return Ok(());
    }

    let zeros = digits.iter().filter(|&&d| d == 0).count();
    if zeros >= 2 {
        println!("YES");
        return Ok(());
    }
    if zeros == 1 {
        println!("NO");
        return Ok(());
    }

    let mut left = 1u64;
    let total: u64 = digits.iter().product();
    
    let found = digits.iter().any(|&d| {
        left *= d;
        left == total / left
    });

    println!("{}", if found { "YES" } else { "NO" });
    Ok(())
}