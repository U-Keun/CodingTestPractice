use std::io::{ self, BufRead };
use std::fmt;
use std::cmp::Ordering;

#[derive(Clone)]
pub struct BigInt {
    sign: i8,
    digits: Vec<u8>,
}

impl BigInt {

    fn normalize(digits: &mut Vec<u8>) {
        while digits.len() > 1 && *digits.last().unwrap() == 0 {
            digits.pop();
        }
    }

    pub fn from_str(s: &str) -> Self {
        let s = s.trim();
        let mut chars = s.chars().peekable();

        let sign = match chars.peek() {
            Some('-') => {
                chars.next();
                -1
            }
            Some('+') => {
                chars.next();
                1
            }
            _ => 1
        };

        let mut digits: Vec<u8> = chars
            .map(|c| c.to_digit(10).unwrap() as u8)
            .rev()
            .collect();
        Self::normalize(&mut digits);

        if digits.is_empty() {
            digits.push(0);
        }

        let sign = if digits == vec![0] { 1 } else { sign };

        BigInt { sign, digits }
    }

    fn add_abs(a: &[u8], b: &[u8]) -> Vec<u8> {
        let mut result = Vec::new();
        let mut carry = 0;
        let max_len = a.len().max(b.len());

        for i in 0..max_len {
            let da = a.get(i).copied().unwrap_or(0);
            let db = b.get(i).copied().unwrap_or(0);
            let sum = da + db + carry;
            result.push(sum % 10);
            carry = sum / 10;
        }

        if carry > 0 {
            result.push(carry);
        }

        result
    }

    fn cmp_abs(a: &[u8], b: &[u8]) -> Ordering {
        if a.len() != b.len() {
            return a.len().cmp(&b.len());
        }

        for i in (0..a.len()).rev() {
            match a[i].cmp(&b[i]) {
                Ordering::Equal => continue,
                non_eq => return non_eq,
            }
        }

        Ordering::Equal
    }

    fn sub_abs_raw(a: &[u8], b: &[u8]) -> Vec<u8> {
        let mut result = Vec::new();
        let mut borrow = 0;
        
        for i in 0..a.len() {
            let da = a[i] as i16;
            let db = b.get(i).copied().unwrap_or(0) as i16;

            let mut sub = da - db - borrow;

            if sub < 0 {
                sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.push(sub as u8);
        }

        Self::normalize(&mut result);

        result
    }

    fn abs_diff(a: &[u8], b: &[u8]) -> Vec<u8> {
        match Self::cmp_abs(a, b) {
            Ordering::Greater | Ordering::Equal => Self::sub_abs_raw(a, b),
            Ordering::Less => Self::sub_abs_raw(b, a),
        }
    }

    fn sub_same_sign(&self, other: &Self) -> Self {
        match Self::cmp_abs(&self.digits, &other.digits) {
            std::cmp::Ordering::Greater => {
                Self {
                    sign: self.sign,
                    digits: Self::abs_diff(&self.digits, &other.digits),
                }
            },
            std::cmp::Ordering::Less => {
                Self {
                    sign: -self.sign,
                    digits: Self::abs_diff(&self.digits, &other.digits),
                }
            },
            std::cmp::Ordering::Equal => {
                Self {
                    sign: 1,
                    digits: vec![0],
                }
            },
        }
    }

    fn mul_abs(a: &[u8], b: &[u8]) -> Vec<u8> {
        let mut result = vec![0u32; a.len() + b.len()];

        for (i, &da) in a.iter().enumerate() {
            let mut carry = 0u32;
            for (j, &db) in b.iter().enumerate() {
                let idx = i + j;
                let tmp = result[idx] + da as u32 * db as u32 + carry;
                result[idx] = tmp % 10;
                carry = tmp / 10;
            }

            let mut idx = i + b.len();
            while carry > 0 {
                let sum = result[idx] + carry;
                result[idx] = sum % 10;
                carry = sum / 10;
                idx += 1;
                if idx == result.len() {
                    result.push(0);
                }
            }
        }

        let mut digits: Vec<u8> = result.into_iter().map(|d| d as u8).collect();
        Self::normalize(&mut digits);
        digits
    }

    pub fn neg(&self) -> Self {
        if self.digits == vec![0] {
            self.clone()
        } else {
            Self {
                sign: -self.sign,
                digits: self.digits.clone(),
            }
        }
    }

    pub fn add(&self, other: &Self) -> Self {
        match (self.sign, other.sign) {
            (1, 1) => Self { sign: 1, digits: Self::add_abs(&self.digits, &other.digits) },
            (-1, -1) => Self { sign: -1, digits: Self::add_abs(&self.digits, &other.digits) },
            (1, -1) => self.sub(&other.neg()),
            (-1, 1) => other.sub(&self.neg()),
            _ => unreachable!(),
        }
    }

    pub fn sub(&self, other: &Self) -> Self {
        match (self.sign, other.sign) {
            (1, 1) => Self::sub_same_sign(self, other),
            (-1, -1) => Self::sub_same_sign(self, other),
            (1, -1) => self.add(&other.neg()),
            (-1, 1) => self.neg().add(other).neg(),
            _ => unreachable!(),
        }
    }

    pub fn mul(&self, other: &Self) -> Self {
        let sign = self.sign * other.sign;
        let digits = Self::mul_abs(&self.digits, &other.digits);
        let sign = if digits == vec![0] { 1 } else { sign };
        
        Self { sign, digits }
    }
}

impl fmt::Display for BigInt {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        let s: String = self.digits.iter().rev().map(|d| (b'0' + d) as char).collect();
        if self.sign < 0 {
            write!(f, "-{}", s)
        } else {
            write!(f, "{}", s)
        }
    }
}

fn read_bigint() -> BigInt {
    let stdin = io::stdin();
    let line = stdin.lock().lines().next().unwrap().unwrap();
    BigInt::from_str(&line)
}

fn main() {
    let a = read_bigint();
    let b = read_bigint();

    println!("{}", a.add(&b));
    println!("{}", a.sub(&b));
    println!("{}", a.mul(&b));
}