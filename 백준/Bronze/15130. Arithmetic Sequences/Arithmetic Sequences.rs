use std::io;
use std::convert::TryInto;

fn main() -> io::Result<()> {
    let mut nums: Vec<i32> = {
        let mut buf = String::new();
        io::stdin().read_line(&mut buf).unwrap();
        buf.trim()
            .split_whitespace()
            .map(|s| s.parse().unwrap())
            .collect()
    };

    let non_zero: Vec<(i32, i32)> = nums
        .iter()
        .enumerate()
        .filter_map(|(i, &v)| if v != 0 { Some((i as i32, v)) } else { None })
        .take(2)
        .collect();

    let [(i1, v1), (i2, v2)]: [(i32, i32); 2] = non_zero
        .try_into()
        .expect("two non-zero");

    let idx_diff = i2 - i1;
    let val_diff = v2 - v1;
    if val_diff % idx_diff != 0 {
        println!("-1");
        return Ok(());
    }

    let d = val_diff / idx_diff;
    let a0 = v1 - d * i1;
    nums.iter_mut()
        .enumerate()
        .for_each(|(i, x)| *x = a0 + d * (i as i32));

    let out = nums
        .into_iter()
        .map(|v| v.to_string())
        .collect::<Vec<_>>()
        .join(" ");
    println!("{}", out);
    Ok(())
}