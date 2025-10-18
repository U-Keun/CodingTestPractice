use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[inline]
fn ceil_div(a: u128, b: u128) -> u128 {
    if a == 0 { 0 } else { (a - 1) / b + 1 }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let d = next!(it, u128);
    let p = next!(it, u128);
    let q = next!(it, u128);

    if p == 1 || q == 1 {
        println!("{}", d);
        return;
    }

    let (l, s) = if p >= q { (p, q) } else { (q, p) };

    let mut ans = ceil_div(d, l) * l;
    ans = ans.min(ceil_div(d, s) * s);

    let limit = (d / l).min(s.saturating_sub(1));
    for i in 0..=limit {
        let paid = i * l;
        if paid >= d {
            ans = ans.min(paid);
            break;
        }
        let rest = d - paid;
        let need = paid + ceil_div(rest, s) * s;
        if need < ans { ans = need; }
    }
    println!("{}", ans);
}