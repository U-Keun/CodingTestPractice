use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn divisors(n: u32) -> Vec<u32> {
    let mut v = Vec::new();
    let mut i = 1u32;
    while i * i <= n {
        if n % i == 0 {
            v.push(i);
            if i * i != n {
                v.push(n / i);
            }
        }
        i += 1;
    }
    v.sort_unstable();
    v
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let a = next!(it, u32);
    let b = next!(it, u32);
    let k = next!(it, usize);
    
    if b % a != 0 {
        println!("-1");
        return;
    }

    if k == 1 {
        if a == b {
            println!("{a}");
        } else {
            println!("-1");
        }
        return;
    }

    let divisors = divisors(b / a);
    if divisors.len() < k {
        println!("-1");
        return;
    }

    let mut nums: Vec<u32> = vec![a; k];
    nums[k - 1] = b;
    
    for j in 1..(k - 1) {
        nums[j] *= divisors[j];
    }

    println!("{}", nums.iter().map(ToString::to_string).collect::<Vec<_>>().join(" "))
}