use std::io::{ self, Read };
use std::collections::{ VecDeque, HashMap };

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
    let mut out = String::new();

    let dirs = [(1i32, 0i32), (-1, 0), (0, 1), (0, -1)];

    for _ in 0..t {
        let h = next!(it, usize);
        let w = next!(it, usize);

        let mut raw: Vec<Vec<u8>> = Vec::with_capacity(h);
        for _ in 0..h {
            let s = it.next().unwrap().as_bytes().to_vec();
            raw.push(s);
        }

        let keys = it.next().unwrap();
        let mut have_key = [false; 26];
        if keys != "0" {
            for b in keys.bytes() {
                let idx = (b - b'a') as usize;
                have_key[idx] = true;
            }
        }

        let mut g = vec![vec![b'.'; w + 2]; h + 2];
        for i in 0..h {
            for j in 0..w {
                g[i + 1][j + 1] = raw[i][j];
            }
        }

        let mut visited = vec![vec![false; w + 2]; h + 2];
        let mut q = VecDeque::new();
        q.push_back((0usize, 0usize));
        visited[0][0] = true;

        let mut wait: HashMap<usize, Vec<(usize, usize)>> = HashMap::new();

        let mut docs = 0usize;
        
        while let Some((x, y)) = q.pop_front() {
            for (dx, dy) in dirs {
                let nx = x as i32 + dx;
                let ny = y as i32 + dy;
                let _h = (h + 2) as i32;
                let _w = (w + 2) as i32;
                if nx < 0 || ny < 0 || nx >= _h || ny >= _w { continue; }
                let (nx, ny) = (nx as usize, ny as usize);
                if visited[nx][ny] { continue; }

                let c = g[nx][ny];
                match c {
                    b'*' => {}
                    b'A'..=b'Z' => {
                        let need = (c - b'A') as usize;
                        if have_key[need] {
                            visited[nx][ny] = true;
                            q.push_back((nx, ny));
                        } else {
                            wait.entry(need).or_default().push((nx, ny));
                        }
                    }
                    b'a'..=b'z' => {
                        let key = (c - b'a') as usize;
                        if !have_key[key] {
                            have_key[key] = true;
                            if let Some(mut doors) = wait.remove(&key) {
                                for (x, y) in doors {
                                    if !visited[x][y] {
                                        visited[x][y] = true;
                                        q.push_back((x, y));
                                    }
                                }
                            }
                        } 
                        visited[nx][ny] = true;
                        q.push_back((nx, ny));
                        g[nx][ny] = b'.';
                    }
                    b'$' => {
                        docs += 1;
                        visited[nx][ny] = true;
                        q.push_back((nx, ny));
                        g[nx][ny] = b'.';
                    }
                    _ => {
                        visited[nx][ny] = true;
                        q.push_back((nx, ny));
                    }
                }
            }
        }
        out.push_str(&format!("{}\n", docs));
    }
    print!("{out}");
}