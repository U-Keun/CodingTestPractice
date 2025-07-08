use std::io::{ self, Read };

fn select(arr: &mut [usize], p: usize, r: usize, q: usize, k: &mut i32) {
    if p == r { return; }
    let t = partition(arr, p, r, k);
    let s = t - p + 1;
    if *k <= 0 { return; }
    if q < s {
        select(arr, p, t - 1, q, k)
    } else if q == s {
        return;
    } else {
        select(arr, t + 1, r, q - s, k);
    }
}

fn partition(arr: &mut [usize], p: usize, r: usize, k: &mut i32) -> usize {
    let x = arr[r];
    let mut i = p;

    for j in p..r {
        if arr[j] <= x {
            arr.swap(i, j);
            *k -= 1;
            if *k == 0 {
                let a = arr[i];
                let b = arr[j];
                if a <= b { println!("{} {}", a, b); }
                else { println!("{} {}", b, a); }
            }
            i += 1;
        }
    }
    if i != r { 
        arr.swap(i, r); 
        *k -= 1;
        if *k == 0 {
            let a = arr[i];
            let b = arr[r];
            if a <= b { println!("{} {}", a, b); }
            else { println!("{} {}", b, a); }
        }
    }
    i
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
    let mut iter = input.split_whitespace()
        .map(|s| s.parse::<usize>().unwrap());

    let n = iter.next().unwrap();
    let q = iter.next().unwrap();
    let mut k = iter.next().unwrap() as i32;
    
    let mut nums: Vec<usize> = iter.collect();
    
    select(&mut nums, 0, n - 1, q, &mut k);

    if k > 0 {
        println!("-1");
    }
}