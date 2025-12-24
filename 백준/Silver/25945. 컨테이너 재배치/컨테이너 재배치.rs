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

    let n = next!(it, usize);
    let mut v = vec![0usize; n];
    let mut total = 0usize;
    for i in 0..n { 
        v[i] = next!(it, usize); 
        total += v[i];
    }

    let s = total / n;
    let mut need = 0usize;
    let mut rest = 0usize;
    for i in 0..n {
        if v[i] == s { continue; }

        if v[i] < s { need += s - v[i]; }
        else { rest += v[i] - s - 1; }
    }

    let ans = rest.max(need);
    println!("{ans}");
}