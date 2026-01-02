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

    let _n = next!(it, usize);
    let s = next!(it, String);
    let chars: Vec<char> = s.chars().collect();

    let q = next!(it, usize);
    for _ in 0..q {
        let l = next!(it, usize) - 1;
        let r = next!(it, usize) - 1;
        let mut ans = 0usize;
        for i in l..r {
            let mut j = i as isize;
            let mut k = i as isize + 1;
            let mut v = 0usize;
            while j >= l as isize && k <= r as isize { 
                if chars[j as usize] == chars[k as usize] { v += 1; }
                j -= 1;
                k += 1;
            }
            ans = ans.max(v);
        }
        println!("{}", ans);
    }
}