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

    let t = next!(it, usize);
    for i in 1..=t {
        let n = next!(it, usize);

        if n == 0 {
            println!("Case #{i}: INSOMNIA");
            continue;
        }

        let mut rec = vec![false; 10];
        let mut cnt = 0usize;
        let mut j = 0usize;
        
        while cnt < 10 {
            j += 1;
            let mut tmp = n * j;
            while tmp > 0 {
                let k = tmp % 10;
                if !rec[k] { cnt += 1; rec[k] = true; }
                tmp /= 10;
            }
        }
        println!("Case #{i}: {}", n * j);
    }
}