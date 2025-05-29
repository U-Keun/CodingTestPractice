use std::io::{ self, Read };

struct Scanner {
    buf: Vec<u8>,
    pos: usize,
}

impl Scanner {
    fn new() -> Self {
        let mut buf = Vec::new();
        io::stdin().read_to_end(&mut buf).unwrap();
        Scanner { buf, pos: 0 }
    }

    fn next<T: std::str::FromStr>(&mut self) -> T {
        while self.pos < self.buf.len() && self.buf[self.pos].is_ascii_whitespace() {
            self.pos += 1;
        }
        let start = self.pos;
        while self.pos < self.buf.len() && !self.buf[self.pos].is_ascii_whitespace() {
            self.pos += 1;
        }
        let token = unsafe { std::str::from_utf8_unchecked(&self.buf[start..self.pos]) };
        token.parse().ok().expect("Failed parse")
    }
}

fn main() {
    let mut sc = Scanner::new();
    let mut a: u16 = sc.next();
    let mut b: u16 = sc.next();

    let mut answer = 1u8;
    while a > 0 || b > 0 {
        if a % 10 != b % 10 {
            answer *= 2;
        }
        a /= 10;
        b /= 10;
    }

    println!("{}", answer);
}