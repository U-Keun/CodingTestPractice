use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let t: usize = next!(it, usize);

    for i in 1..=t {
        let n: usize = next!(it, usize);

        let _a: u32 = next!(it, u32);
        let _b: u32 = next!(it, u32);

        for _ in 0..n {
            let _u = next!(it, usize);
            let _v = next!(it, usize);
        }
        
        println!("Material Management {i}");
        println!("Classification ---- End!");
    }
}