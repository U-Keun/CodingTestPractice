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

    let n: usize = next!(it, usize);
    
    let mut nums: Vec<usize> = (0..n)
        .map(|_| {
            let num = next!(it, usize);
            num
        })
    .collect();

    let k: usize = next!(it, usize);

    let cut: usize = n / k;

    for slice in nums.chunks_mut(cut) {
        slice.sort_unstable();
    }

    println!("{}", nums.iter().map(|x| x.to_string())
        .collect::<Vec<_>>()
        .join(" "));
}