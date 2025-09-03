use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone, Copy)]
struct Vertical {
    x: i64,
    y1: i64,
    y2: i64,
    covered: i32,
}

#[derive(Clone, Copy, Default)]
struct Node {
    cover: i32,
    len: i64,
}

struct SegTree {
    n: usize,
    y_coord: Vec<i64>,
    tree: Vec<Node>,
}
impl SegTree {
    fn new(y_coord: Vec<i64>) -> Self {
        let n = y_coord.len() - 1;
        Self { n, y_coord, tree: vec![Node::default(); 4 * n] }
    }
    fn update(&mut self, idx: usize, l: usize, r: usize, s: usize, e: usize, v: i32) {
        if e <= l || r <= s { return; }
        if s <= l && r <= e {
            self.tree[idx].cover += v;
            self.pull(idx, l, r);
            return;
        }
        let mid = (l + r) / 2;
        self.update(idx * 2, l, mid, s, e, v);
        self.update(idx * 2 + 1, mid, r, s, e, v);
        self.pull(idx, l, r);
    }
    fn pull(&mut self, idx: usize, l: usize, r: usize) {
        if self.tree[idx].cover > 0 {
            self.tree[idx].len = self.y_coord[r] -  self.y_coord[l];
        } else {
            if r - l == 1 {
                self.tree[idx].len = 0;
            } else {
                self.tree[idx].len = self.tree[idx * 2].len + self.tree[idx * 2 + 1].len;
            }
        }
    }
    #[inline]
    fn covered_len(&self) -> i64 {
        self.tree[1].len
    }
    fn range_of(&self, y1: i64, y2: i64) -> (usize, usize) {
        let l = self.y_coord.binary_search(&y1).unwrap();
        let r = self.y_coord.binary_search(&y2).unwrap();
        (l, r)
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut ver: Vec<Vertical> = Vec::with_capacity(n * 2);
    let mut y_coord: Vec<i64> = Vec::with_capacity(n * 2);

    for _ in 0..n {
        let x1 = next!(it, i64);
        let y1 = next!(it, i64);
        let x2 = next!(it, i64);
        let y2 = next!(it, i64);
        ver.push(Vertical { x: x1, y1, y2, covered: 1});
        ver.push(Vertical { x: x2, y1, y2, covered: -1});
        y_coord.push(y1);
        y_coord.push(y2);
    }

    y_coord.sort_unstable();
    y_coord.dedup();

    let mut seg = SegTree::new(y_coord.clone());

    ver.sort_by_key(|e| (e.x, e.covered));

    let mut area = 0i64;
    let mut prev_x: i64 = ver[0].x;
    
    let mut i = 0;
    while i < ver.len() {
        let cur_x = ver[i].x;
        let dx = cur_x - prev_x;
        if dx != 0 {
            area += seg.covered_len() * dx;
            prev_x = cur_x;
        }
        while i < ver.len() && ver[i].x == cur_x {
            let (l, r) = seg.range_of(ver[i].y1, ver[i].y2);
            if l < r {
                seg.update(1, 0, seg.n, l, r, ver[i].covered);
            }
            i += 1;
        }
    }

    println!("{}", area);
}