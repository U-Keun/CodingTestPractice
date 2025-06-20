use std::io::{ self, BufRead };

fn main() -> io::Result<()> {
    let mut reader = io::stdin().lock();
    let mut buf = String::new();

    reader.read_line(&mut buf)?;
    let mut iter = buf
        .trim()
        .split_whitespace()
        .map(|s| s.parse::<usize>().expect("Failed to parse"));   
    let n = iter.next().unwrap();
    let m = iter.next().unwrap();
    
    buf.clear();

    let mut grid: [[usize; 101]; 101] = [[0; 101]; 101];
    for _ in 0..n {
        reader.read_line(&mut buf)?;
        let mut it = buf.split_whitespace()
            .map(|s| s.parse::<usize>().unwrap());
        let (r1, c1) = (it.next().unwrap(), it.next().unwrap());
        let (r2, c2) = (it.next().unwrap(), it.next().unwrap());
        buf.clear();

        for row in &mut grid[r1..=r2] {
            for cell in &mut row[c1..=c2] {
                *cell += 1;
            }
        }
    }

    let mut answer = 0usize;
    for row in grid {
        for cell in row {
            if cell > m {
                answer += 1;
            }
        }
    }
    
    println!("{}", answer);
    Ok(())
}