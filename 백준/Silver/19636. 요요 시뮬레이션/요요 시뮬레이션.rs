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

    let w0 : i32 = next!(it, i32);
    let i0 : i32 = next!(it, i32);
    let t : i32 = next!(it, i32);


    let d : i32 = next!(it, i32);
    let i : i32 = next!(it, i32);
    let a : i32 = next!(it, i32);

    let w = w0 + (i - i0 - a) * d;
    if w <= 0 { println!("Danger Diet"); }
    else {
        println!("{} {}", w0 + (i - i0 - a) * d, i0);
    }

    let mut w = w0;
    let mut i1 = i0;
    for _ in 0..d {
        let dif = i - i1 - a;
        w += dif;
        if dif.abs() > t {
            if dif < 0 {
                i1 += dif / 2 + dif % 2;
            } else { i1 += dif / 2; }
        }
    }

    if w <= 0 || i1 <= 0 {
        println!("Danger Diet");
    } else {
        let msg = if i0 > i1 { "YOYO" } else { "NO" };
        println!("{} {} {}", w, i1, msg);
    }

}