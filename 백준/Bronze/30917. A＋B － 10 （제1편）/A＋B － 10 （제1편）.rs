fn read_i32() -> i32 {
    let mut resp_s = String::new();
    std::io::stdin().read_line(&mut resp_s).unwrap();
    resp_s.trim().parse().unwrap()
}

fn main() {
    for a in 1..=9 {
        println!("? A {a}");
        let resp = read_i32();

        if resp == 1 {
            for b in 1..=9 {
                println!("? B {b}");
                let resp = read_i32();
                if resp == 1 {
                    println!("! {}", a + b);
                    break;
                }
            }
            break;
        }
    }
}