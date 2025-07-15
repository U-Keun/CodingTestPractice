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

    let n: usize = next!(it, usize);
    let mut fixed = Vec::new();
    let mut arr: Vec<usize> = (0..n)
        .map(|i| {
            let h: usize = next!(it, usize);
            if h == i + 1 {
                fixed.push(i);
            }
            h
        })
        .collect();
    
    if fixed.is_empty() {
        println!("0\n{}", arr.iter().map(|v| v.to_string()).collect::<Vec<_>>().join(" "));
        return;
    }

    let root = (0..n).find(|&i| arr[i] != i + 1).unwrap_or(fixed[0]);
    let mut changes = 0;

    for &i in &fixed {
        if i == root { continue; }
        arr[i] = root + 1;
        changes += 1;
    }

    if arr[root] == root + 1 {
        let other = if root != 0 { 0 } else { 1 };
        arr[root] = other + 1;
        changes += 1;
    }

    println!("{changes}\n{}", arr.iter().map(|v| v.to_string()).collect::<Vec<_>>().join(" "));
}