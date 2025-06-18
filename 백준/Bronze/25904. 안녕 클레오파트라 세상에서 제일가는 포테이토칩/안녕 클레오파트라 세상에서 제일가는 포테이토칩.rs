use std::io::{ self, Read };

fn main() -> io::Result<()> {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf)?;

    let mut it = buf
        .split_whitespace()
        .map(|s| s.parse::<usize>().unwrap());

    let n: usize = it.next().unwrap();
    let x: usize = it.next().unwrap();

    let s: Vec<usize> = it.collect();

    let answer = s
        .iter()
        .enumerate()
        .map(|(i, &si)| {
            let init = x + i;
            let v = if init > si {
                init
            } else {
                let extra = (si - init) / n + 1;
                init + extra * n
            };
            (i + 1, v)
        })
        .min_by_key(|&(_, v)| v)
        .unwrap()
        .0;

    println!("{}", answer);
    Ok(())
}