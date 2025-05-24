use std::io;

fn main() {
    let nums = read_nums();
    match find_two_nonzero(&nums) {
        Some((i1, v1, i2, v2)) => {
            if let Some(seq) = build_sequence(i1, v1, i2, v2, nums.len()) {
                print_seq(&seq);
            } else {
                println!("-1");
            }
        }
        None => {
            println!("-1");
        }
    }
}

fn read_nums() -> Vec<i32> {
    let mut buf = String::new();
    io::stdin().read_line(&mut buf).unwrap();
    buf.split_whitespace()
        .map(|s| s.parse().expect("invalid integer"))
        .collect()
}

fn find_two_nonzero(nums: &[i32]) -> Option<(usize, i32, usize, i32)> {
    let mut it = nums.iter().enumerate().filter(|&(_, &v)| v != 0);
    let (i1, &v1) = it.next()?;
    let (i2, &v2) = it.next()?;
    Some((i1, v1, i2, v2))
}

fn build_sequence(i1: usize, v1: i32, i2: usize, v2: i32, len: usize) -> Option<Vec<i32>> {
    let idx_diff = (i2 as i32) - (i1 as i32);
    let val_diff = v2 - v1;
    if idx_diff == 0 || val_diff % idx_diff != 0 {
        return None;
    }
    let d = val_diff / idx_diff;
    let a0 = v1 - d * (i1 as i32);
    Some((0..len).map(|i| a0 + d * (i as i32)).collect())
}

fn print_seq(seq: &[i32]) {
    let out = seq.iter().map(ToString::to_string).collect::<Vec<_>>().join(" ");
    println!("{}", out);
}