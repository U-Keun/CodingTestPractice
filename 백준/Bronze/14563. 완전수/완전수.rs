use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn sum_of_div(num: u32) -> u32 {
    let mut sum = 0u32;
    for k in 1..=(num / 2) {
        if num % k == 0 {
            sum += k;
        }
    }
    sum
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let t: usize = next!(it, usize);
    for _ in 0..t {
        let num: u32 = next!(it, u32);
        let sum: u32 = sum_of_div(num);

        if sum == num {
            println!("Perfect");
        } else if sum > num {
            println!("Abundant");
        } else {
            println!("Deficient");
        }
    }
}