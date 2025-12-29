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

    let r = next!(it, usize);
    let s = next!(it, usize);

    let mut grid = vec![vec![b'#'; s + 2]; r + 2];
    for i in 0..r {
       let row = it.next().unwrap().as_bytes();
       grid[i + 1][1..=s].copy_from_slice(row);
    }

    const DIR: [(isize, isize); 8] = [
        (-1, -1), (-1, 0), (-1, 1),
        ( 0, -1),          ( 0, 1),
        ( 1, -1), ( 1, 0), ( 1, 1),
    ];

    let mut hs = 0usize;
    let mut rec = 0usize;
    for i in 1..=r {
        for j in 1..=s {
            if grid[i][j] == b'.' {
                let mut v = 0usize;
                for (dr, dc) in DIR {
                    let nr = (i as isize + dr) as usize;
                    let nc = (j as isize + dc) as usize;
                    if grid[nr][nc] == b'o' { v += 1; }
                }
                rec = rec.max(v);
            } else {
                for k in 4..8 {
                    let dr = DIR[k].0;
                    let dc = DIR[k].1;
                    let nr = (i as isize + dr) as usize;
                    let nc = (j as isize + dc) as usize;
                    if grid[nr][nc] == b'o' { hs += 1; }
                }
            }
        }
    }

    println!("{}", hs + rec);
}