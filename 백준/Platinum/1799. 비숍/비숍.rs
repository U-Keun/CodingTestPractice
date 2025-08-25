use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn solve_by_color(board: &Vec<Vec<u8>>, n: usize, color: usize) -> i32 {
    let mut diag = vec![false; 2 * n - 1];
    let mut best = 0;

    fn dfs(sum: usize, n: usize, board: &Vec<Vec<u8>>, diag: &mut [bool], cur: i32,
        best: &mut i32, color: usize) {
        if sum >= 2 * n - 1 { 
            *best = (*best).max(cur);
            return;
        }

        dfs(sum + 2, n, board, diag, cur, best, color);

        let from = sum.saturating_sub(n - 1);
        let to = sum.min(n - 1);

        for r in from..=to {
            let c = sum - r;
            if board[r][c] == 0 { continue; }
            let d = (r as isize - c as isize + (n as isize - 1)) as usize;
            if diag[d] { continue; }
            diag[d] = true;
            dfs(sum + 2, n, board, diag, cur + 1, best, color);
            diag[d] = false;
        }
    }

    dfs(color, n, board, &mut diag, 0, &mut best, color);
    best
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut board = vec![vec![0u8; n]; n];
    for i in 0..n {
        for j in 0..n {
            board[i][j] = next!(it, u8);
        }
    }

    let black = solve_by_color(&board, n, 0);
    let white = solve_by_color(&board, n, 1);

    println!("{}", black + white);
}