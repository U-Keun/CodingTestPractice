use std::io::{ self, BufRead };

fn main() {
    let lines = io::stdin().lock().lines().take(7);

    let (sum, min_opt) = lines
        .filter_map(|line| {
            line.ok()
                .and_then(|s| s.trim().parse::<u32>().ok())
        })
        .filter(|&n| n % 2 == 1)
        .fold((0u32, None::<u32>), |(sum, min_opt), n| {
            let new_min = Some(min_opt.map_or(n, |m| m.min(n)));
            (sum + n, new_min)
        });
    
    match min_opt {
        Some(min_odd) => {
            println!("{}", sum);
            println!("{}", min_odd);
        }
        None => {
            println!("-1");
        }
    } 
}