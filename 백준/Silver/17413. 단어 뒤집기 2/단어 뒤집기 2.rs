use std::io;

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let mut stack = Vec::new();
    let mut is_open = false;
    for ch in input.trim().chars() {
        if is_open {
            print!("{}", ch);
            if ch == '>' {
                is_open = false;
            }
            continue;
        }

        if ch == '<' {
            while let Some(top) = stack.pop() {
                print!("{}", top);
            }
            print!("{}", ch);
            is_open = true;
            continue;
        } else if ch == ' ' {
            while let Some(top) = stack.pop() {
                print!("{}", top);
            }
            print!(" ");
            continue;
        }

        stack.push(ch);
    }
    while let Some(top) = stack.pop() {
        print!("{}", top);
    }
    println!("");
}