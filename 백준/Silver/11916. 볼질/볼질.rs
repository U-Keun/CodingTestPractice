use std::io::{ self, Read };

fn walk(bases: &mut [bool; 3], score: &mut usize) {
    if !bases[0] {
        bases[0] = true;
    } else if !bases[1] {
        bases[1] = true;
    } else if !bases[2] {
        bases[2] = true;
    } else {
        *score += 1;
    }
}

fn wild_pitch(bases: &mut [bool; 3], score: &mut usize) {
    if bases[2] {
        *score += 1;
    }
    bases[2] = bases[1];
    bases[1] = bases[0];

    bases[0] = false;
}

fn main() {
    let mut input = String::new();
    io::stdin().read_to_string(&mut input).unwrap();
    let mut iter = input.split_whitespace()
        .map(|s| s.parse::<usize>().unwrap());

    let n = iter.next().unwrap();

    let mut balls = 0;
    let mut score = 0;
    let mut bases = [false; 3];

    for _ in 0..n {
        match iter.next().unwrap() {
            1 => {
                balls += 1;
                if balls == 4 {
                    walk(&mut bases, &mut score);
                    balls = 0;
                }
            }
            2 => {
                walk(&mut bases, &mut score);
                balls = 0;
            }
            3 => {
                balls += 1;
                wild_pitch(&mut bases, &mut score);
                if balls == 4 {
                    walk(&mut bases, &mut score);
                    balls = 0;
                }
            }
            _ => {}
        }
    }

    println!("{}", score);
}