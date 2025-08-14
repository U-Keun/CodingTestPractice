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

    let t = next!(it, usize);
    for i in 1..=t {
        let mut grid = Vec::new();
        for _ in 0..3 {
            let line = it.next().unwrap();
            let row: Vec<char> = line.trim().chars().collect();
            grid.push(row);
        }
        let mark = next!(it, char);

        let mut positions = Vec::new();
        for i in 0..3 {
            for j in 0..3 {
                if grid[i][j] == mark {
                    positions.push((i, j));
                }
            }
        }

        let (r0, c0) = positions[0];
        let (r1, c1) = positions[1];

        if r0 == r1 {
            for c in 0..3 {
                if grid[r0][c] == '-' {
                    grid[r0][c] = mark;
                    break;
                }
            }
        } else if c0 == c1 {
            for r in 0..3 {
                if grid[r][c0] == '-' {
                    grid[r][c0] = mark;
                    break;
                }
            }
        } else {
            if r0 == c0 && r1 == c1 {
                for k in 0..3 {
                    if grid[k][k] == '-' {
                        grid[k][k] = mark;
                        break;
                    }
                }
            } else {
                for k in 0..3 {
                    if grid[k][2 - k] == '-' {
                        grid[k][2 - k] = mark;
                        break;
                    }
                }
            }
        }

        println!("Case {i}:");
        for r in 0..3 {
            let line: String = grid[r].iter().collect();
            println!("{line}");
        }
        
    }

}