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

    let mut total = 0usize;
    let mut heights: Vec<usize> = (0..9)
        .map(|_| {
            let h = next!(it, usize);
            total += h;
            h
        })
        .collect();

    heights.sort();

    for i in 0..9 {
        for j in i..9 {
            if total - heights[i] - heights[j] == 100 {
                for k in 0..9 {
                    if k == i || k == j { continue; }
                    println!("{}", heights[k]);
                }
                return;
            }
        }
    }


}