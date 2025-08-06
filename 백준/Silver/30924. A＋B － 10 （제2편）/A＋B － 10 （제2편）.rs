struct XorShift32(u32);
impl XorShift32 {
    fn new(seed: u32) -> Self { Self(seed) }
    fn next_u32(&mut self) -> u32 {
        let mut x = self.0;
        x ^= x << 13;
        x ^= x >> 17;
        x ^= x << 5;
        self.0 = x;
        x
    }
}

fn shuffled_numbers(rng: &mut XorShift32) -> Vec<u16> {
    let mut v: Vec<u16> = (1..=10000).collect();
    for i in (1..v.len()).rev() {
        let j = (rng.next_u32() as usize) % (i + 1);
        v.swap(i, j);
    }
    v
}

fn read_i32() -> i32 {
    let mut resp_s = String::new();
    std::io::stdin().read_line(&mut resp_s).unwrap();
    resp_s.trim().parse().unwrap()
}

fn find_value(label: &str, rng: &mut XorShift32) -> u16 {
    let mut nums = shuffled_numbers(rng);
    loop {
        let x = nums.pop().unwrap();
        println!("? {} {}", label, x);
        
        let resp = read_i32();
        if resp == 1 { return x; }
    }
}

fn main() {
    let seed = 0xCAFEBABE ^ (std::time::SystemTime::now()
        .duration_since(std::time::UNIX_EPOCH).unwrap().subsec_nanos());
    let mut rng = XorShift32::new(seed);

    let a = find_value("A", &mut rng);
    let b = find_value("B", &mut rng);

    println!("! {}", a as u32 + b as u32);
}
