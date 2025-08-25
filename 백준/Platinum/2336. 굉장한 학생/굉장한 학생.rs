use std::io::{ self, Read };
use std::cmp::min;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

struct SegTree {
    _n: usize,
    tree: Vec<usize>,
}

impl SegTree {
    fn new(n: usize) -> Self {
        let size = 1 << (n.next_power_of_two().trailing_zeros() + 1);
        Self {
            _n: n,
            tree: vec![usize::MAX; size],
        }
    }

    fn update(&mut self, mut idx: usize, val: usize) {
        idx += self.tree.len() / 2;
        self.tree[idx] = min(self.tree[idx], val);
        while idx > 1 {
            idx /= 2;
            self.tree[idx] = min(self.tree[idx * 2], self.tree[idx * 2 + 1]);
        }
    }

    fn query(&self, mut l: usize, mut r: usize) -> usize {
        let mut res = usize::MAX;
        let mut n = self.tree.len() / 2;
        l += n; r += n;
        while l <= r {
            if l % 2 == 1 { res = min(res, self.tree[l]); l += 1; }
            if r % 2 == 0 { res = min(res, self.tree[r]); r -= 1; }
            l /= 2;
            r /= 2;
        }
        res
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);

    let mut ex1 = vec![0; n + 1];
    let mut ex2 = vec![0; n + 1];
    let mut ex3 = vec![0; n + 1];

    for rank in 1..=n {
        let id = next!(it, usize);
        ex1[id] = rank;
    }

    for rank in 1..=n {
        let id = next!(it, usize);
        ex2[id] = rank;
    }
    for rank in 1..=n {
        let id = next!(it, usize);
        ex3[id] = rank;
    }

    let mut students: Vec<(usize, usize, usize)> = (1..=n)
        .map(|id| (ex1[id], ex2[id], ex3[id]))
        .collect();

    students.sort_by_key(|&(a, _, _)| a);

    let mut seg = SegTree::new(n);
    let mut ans = 0;

    for &(_, b, c) in &students {
        let best = seg.query(1, b - 1);
        if best > c {
            ans += 1;
        }
        seg.update(b, c);
    }
    
    println!("{}", ans);
}