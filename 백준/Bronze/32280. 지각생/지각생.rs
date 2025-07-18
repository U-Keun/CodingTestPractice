use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, PartialOrd, Ord)]
struct Time { h: u32, m: u32 }

fn parse_time(s: &str) -> Time {
    let mut it = s.split(':').map(|x| x.parse::<u32>().unwrap());
    Time {
        h: it.next().unwrap(),
        m: it.next().unwrap(),
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n: usize = next!(it, usize);

    let mut students = Vec::with_capacity(n);
    let mut teacher: Option<Time> = None;    

    for _ in 0..=n {
        let time_raw = it.next().unwrap();
        let p = it.next().unwrap();
        let time = parse_time(time_raw);

        match p {
            "student" => students.push(time),
            "teacher" => {
                if teacher.replace(time).is_some() {
                    panic!("error");
                }
            }
            other => {}
        }
    }
    
    let school_start_raw = it.next().unwrap();
    let school_start = parse_time(school_start_raw);

    let tc = teacher.unwrap();

    let count = students
        .iter()
        .filter(|&&t| tc <= t && school_start <= t)
        .count();
    println!("{}", count);
}