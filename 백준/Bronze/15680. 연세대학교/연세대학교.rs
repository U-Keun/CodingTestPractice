use std::io;

fn main() {
    let num: u32 = {
		let mut buf = String::new();
		io::stdin().read_line(&mut buf).unwrap();
		buf.trim().parse().unwrap()
	};
    if num == 0 {
        println!("YONSEI");
    } else {
        println!("Leading the Way to the Future");
    }
}