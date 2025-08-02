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

    let r: usize = next!(it, usize);
    let c: usize = next!(it, usize);

    let board: Vec<Vec<char>> = (0..r)
        .map(|_| {
            let row_str = it.next().unwrap();
            let row: Vec<char> = row_str.chars().collect();
            row
        })
        .collect();

    let mut counts = vec![0usize; 5];

    const OFF: [(usize, usize); 4] = [(0, 0), (1, 0), (0, 1), (1, 1)];
    for i in 0..(r - 1) {
        for j in 0..(c - 1) {
            let cells = OFF.map(|(dy, dx)| board[i + dy][j + dx]);
            if cells.iter().any(|&ch| ch == '#') { continue; }
            counts[cells.iter().filter(|&&ch| ch == 'X').count()] += 1;
        }
    }
    
    counts.iter().for_each(|&n| println!("{n}"));
}