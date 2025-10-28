use std::io::{ self, Read };
use std::collections::VecDeque;
macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[inline]
fn in_range(x: i32, y: i32, w: usize, h: usize) -> bool {
    0 <= x && x < w as i32 && 0 <= y && y < h as i32
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let (n, m) = (next!(it, usize), next!(it, usize));

    let mut board: Vec<Vec<u8>> = Vec::with_capacity(n);
    let (mut rx, mut ry, mut bx, mut by) = (0i32, 0i32, 0i32, 0i32);
    for i in 0..n {
        let s = it.next().unwrap().as_bytes().to_vec();
        board.push(s);
        for j in 0..m {
            match board[i][j] {
                b'R' => { ry = i as i32; rx = j as i32; board[i][j] = b'.'; }
                b'B' => { by = i as i32; bx = j as i32; board[i][j] = b'.'; }
                _ => {}
            }
        }
    }

    let dirs: [(i32, i32); 4] = [(1, 0), (-1, 0), (0, 1), (0, -1)];

    let mut visited = vec![vec![vec![vec![false; m]; n]; m]; n];

    let mut q = VecDeque::new();
    visited[ry as usize][rx as usize][by as usize][bx as usize] = true;
    q.push_back((rx, ry, bx, by, 0));

    let mut answer = -1;

    let roll = |mut y: i32, mut x: i32, dy: i32, dx: i32| -> (i32, i32, i32, bool) {
        let mut step = 0;
        loop {
            let ny = y + dy;
            let nx = x + dx;
            if !in_range(nx, ny, m, n) { break; }
            let c = board[ny as usize][nx as usize];
            if c == b'#' { break; }
            y = ny; x = nx; step += 1;
            if c == b'O' { return (x, y, step, true); }
        }
        (x, y, step, false)
    };

    'bfs: while let Some((rx, ry, bx, by, d)) = q.pop_front() {
        if d >= 10 { continue; }

        for &(dy, dx) in &dirs {
            let (mut nrx, mut nry, rstep, r_in) = roll(ry, rx, dy, dx);
            let (mut nbx, mut nby, bstep, b_in) = roll(by, bx, dy, dx);

            if b_in { continue; }

            if r_in {
                answer = d + 1;
                break 'bfs;
            }

            if nrx == nbx && nry == nby {
                if rstep > bstep {
                    nry -= dy; nrx -= dx;
                } else {
                    nby -= dy; nbx -= dx;
                }
            }

            if !visited[nry as usize][nrx as usize][nby as usize][nbx as usize] {
                visited[nry as usize][nrx as usize][nby as usize][nbx as usize] = true;
                q.push_back((nrx, nry, nbx, nby, d + 1));
            }
        }
    }

    println!("{}", answer);
}