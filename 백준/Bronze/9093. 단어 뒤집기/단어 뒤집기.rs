use std::io;

fn flush_stack(stack: &mut Vec<char>) {
    while let Some(top) = stack.pop() {
        print!("{}", top);
    }
}

fn main() {
    let mut input = String::new();
    io::stdin().read_line(&mut input).unwrap();

    let t: u32 = input.trim().parse().unwrap();

    for _ in 0..t {
        let mut line = String::new();
        io::stdin().read_line(&mut line).unwrap();

        let mut stack = Vec::new();
        for ch in line.trim().chars() {
            match ch {
                ' ' => {
                    flush_stack(&mut stack);
                    print!("{}", ch);
                },
                _ => stack.push(ch),
            }
        }
        flush_stack(&mut stack);
        println!("");
    }
}