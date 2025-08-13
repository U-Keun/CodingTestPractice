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

    let finger = next!(it, u64);
    let count = next!(it, u64);

    let mut answer = 0u64;
    if finger == 1 {
        answer += 8 * count;
    } else if finger == 5 {
        answer += 4 + 8 * count;
    } else {
        answer += 8 * (count / 2);
        if count % 2 == 0 {
            answer += finger - 1;
        } else {
            answer += 10 - finger - 1;
        }
    }
    println!("{answer}");
}