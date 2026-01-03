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

    let t = next!(it, usize);
    for _ in 0..t {
        let s = next!(it, String);
        let chars: Vec<char> = s.chars().collect();

        let (mut cur_x, mut cur_y): (isize,isize) = (0isize, 0isize);
        let (mut top, mut bottom, mut left, mut right): (isize, isize, isize, isize) =
            (0isize, 0isize, 0isize, 0isize);
        let mut dir = 1usize; // 1: up, 2: left, 3: down, 4: right

        for c in chars {
            match c {
                'F' => {
                    match dir {
                        1 => { cur_y += 1; top = top.max(cur_y); },
                        2 => { cur_x -= 1; left = left.min(cur_x); },
                        3 => { cur_y -= 1; bottom = bottom.min(cur_y); },
                        4 => { cur_x += 1; right = right.max(cur_x); }
                        _ => {}
                    }
                },
                'B' => {
                    match dir {
                        1 => { cur_y -= 1; bottom = bottom.min(cur_y); },
                        2 => { cur_x += 1; right = right.max(cur_x); },
                        3 => { cur_y += 1; top = top.max(cur_y); },
                        4 => { cur_x -= 1; left = left.min(cur_x); }
                        _ => {}
                    }
                },
                'L' => { dir = if dir == 4 { 1 } else { dir + 1 }; },
                'R' => { dir = if dir == 1 { 4 } else { dir - 1 }; },
                _ => {}
            }
        }
        let ans = (top - bottom) * (right - left);
        println!("{ans}");
    }
}