use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn top_deg(a: &[bool]) -> Option<usize> {
    for i in (0..a.len()).rev() {
        if a[i] { return Some(i); }
    }
    None
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let t = next!(it, usize);
    for _ in 0..t {
        let l = next!(it, usize);
        let mut f = Vec::new();
        for val in (0..l).rev() {
            let tmp = next!(it, usize);
            if tmp == 1 { f.push(val); }
        }

        let m = next!(it, usize);
        let mut g = Vec::new();
        for val in (0..m).rev() {
            let tmp = next!(it, usize);
            if tmp == 1 { g.push(val); }
        }

        let n = next!(it, usize);
        let mut h = Vec::new();
        for val in (0..n).rev() {
            let tmp = next!(it, usize);
            if tmp == 1 { h.push(val); }
        }

        let df = f.first().copied().unwrap_or(0);
        let dg = g.first().copied().unwrap_or(0);
        let mut fg = vec![false; df + dg + 1];

        for &a in &f { for &b in &g { fg[a + b] ^= true; } }

        let dh = h[0];
        while let Some(dp) = top_deg(&fg) {
            if dp < dh { break; }
            let shift = dp - dh;

            for &e in &h {
                fg[e + shift] ^= true;
            }
        }

        let deg = top_deg(&fg).unwrap_or(0) + 1;

        print!("{deg} ");
        for i in (0..deg).rev() {
            print!("{} ", if fg[i] { '1' } else { '0' });
        }
        println!();
    }
}