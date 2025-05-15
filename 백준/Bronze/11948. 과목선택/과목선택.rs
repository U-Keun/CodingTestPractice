use std::io::{ self, BufRead };

fn main() {
    let mut it = io::stdin()
        .lock()
        .lines()
        .map(|l| l.unwrap().trim().parse::<u32>().unwrap());

    let mut sci: [u32; 4] = std::array::from_fn(|_| it.next().unwrap());
    sci.sort_unstable();
    let sum_sci = sci.iter().skip(1).sum::<u32>();

    let soc = it.next().unwrap().max(it.next().unwrap());

    println!("{}", sum_sci + soc);
}