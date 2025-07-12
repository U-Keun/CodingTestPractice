use std::io::{ self, Read };
use std::str::SplitWhitespace;

macro_rules! next {
    ($iter: expr, $t: ty) => {
        $iter.next().unwrap().parse::<$t>().unwrap()
    };
}

fn read_matrix(it: &mut SplitWhitespace<'_>, n: usize) -> Vec<Vec<u8>> {
    let mut mat = Vec::with_capacity(n);
    for _ in 0..n {
        let mut row = Vec::with_capacity(n);
        for _ in 0..n {
            row.push(next!(it, u8));
        }
        mat.push(row);
    }
    mat
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();

    let mut it = input.split_whitespace();

    let n: usize = next!(it, usize);
    let a = read_matrix(&mut it, n);
    let b = read_matrix(&mut it, n);

    let mut answer = 0u32;
    for i in 0..n {
        for j in 0..n {
            let mut val = 0u8;
            for k in 0..n {
                let c = a[i][k] & b[k][j];
                val |= c;
            }
            if val == 1 {
                answer += 1;
            }
        }
    }
    println!("{}", answer);
}