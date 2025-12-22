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

    let mut x = vec![0f64; 3];
    let mut y = vec![0f64; 3];
    for i in 0..3 {
        x[i] = next!(it, f64);
        y[i] = next!(it, f64);
    }

    if (x[0] == x[1] && x[1] == x[2]) ||
        ((x[0] - x[1]) * (y[1] - y[2]) == (y[0] - y[1]) * (x[1] - x[2])) {
        println!("-1.0");
        return;
    }

    let mut d = vec![0f64; 3];
    let pair: [(usize, usize); 3] = [(0, 1), (0, 2), (1, 2)];
    for i in 0..3 {
        let dx = x[pair[i].0] - x[pair[i].1];
        let dy = y[pair[i].0] - y[pair[i].1];
        d[i] = (dx * dx + dy * dy).sqrt();
    }
    
    let mut s: f64 = 60000.0;
    let mut b: f64 = 0.0;
    for i in 0..3 {
        for j in i + 1..3 {
            s = s.min(2.0 * (d[i] + d[j]));
            b = b.max(2.0 * (d[i] + d[j]));
        }
    }

    println!("{}", b - s);
}