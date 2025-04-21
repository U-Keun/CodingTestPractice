use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let num: u32 = input.trim().parse().unwrap(); 

    for i in 1..num {
        let spaces = " ".repeat((num - i) as usize);
        let stars = "*".repeat(i as usize); 
														      
        println!("{}{}", spaces, stars);
    }

    for i in 0..num {
        let spaces = " ".repeat(i as usize);
        let stars = "*".repeat((num - i) as usize);
        println!("{}{}", spaces, stars);
    }
}