use std::io::{ self, Read };
use std::cmp::{ min, max };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone, Copy, Debug)]
struct Node {
    min_val: i32,
    max_val: i32,
}
impl Node {
    #[inline]
    fn single(v: i32) -> Self { Self { min_val: v, max_val: v } }
    #[inline]
    fn merge(a: Self, b: Self) -> Self {
        Self { min_val: min(a.min_val, b.min_val), max_val: max(a.max_val, b.max_val) }
    }
}

struct SegTree {
    n: usize,
    offset: usize,
    tree: Vec<Node>,
}
impl SegTree {
    fn new(init: &[i32]) -> Self {
        let n = init.len();
        let mut offset = 1usize;
        while offset < n { offset <<= 1; }
        let mut tree = vec![Node { min_val: i32::MAX, max_val: i32::MIN }; offset * 2];
        for i in 0..n {
            tree[offset + i] = Node::single(init[i]);
        }
        for i in (1..offset).rev() {
            tree[i] = Node::merge(tree[i << 1], tree[i << 1 | 1]);
        }
        Self { n, offset, tree }
    }
    #[inline]
    fn update(&mut self, mut idx: usize, v: i32) {
        idx += self.offset;
        self.tree[idx] = Node::single(v);
        idx >>= 1;
        while idx >= 1 {
            self.tree[idx] = Node::merge(self.tree[idx << 1], self.tree[idx << 1 | 1]);
            if idx == 1 { break; }
            idx >>= 1;
        }
    }
    #[inline]
    fn query(&self, mut l: usize, mut r: usize) -> Node {
        l += self.offset;
        r += self.offset;
        let mut left = Node { min_val: i32::MAX, max_val: i32::MIN };
        let mut right = Node { min_val: i32::MAX, max_val: i32::MIN };
        while l <= r {
            if (l & 1) == 1 {
                left = Node::merge(left, self.tree[l]);
                l += 1;
            }
            if (r & 1) == 0 {
                right = Node::merge(self.tree[r], right);
                r -= 1;
            }
            l >>= 1; r >>= 1;
        }
        Node::merge(left, right)
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let t = next!(it, usize);
    let mut out = String::new();

    for _ in 0..t {
        let n = next!(it, usize);
        let k = next!(it, usize);

        let mut arr: Vec<i32> = (0..n as i32).collect();
        let mut seg = SegTree::new(&arr);

        for _ in 0..k {
            let q = next!(it, i32);
            let mut a = next!(it, usize);
            let mut b = next!(it, usize);

            if q == 1 {
                if a > b { std::mem::swap(&mut a, &mut b); }
                let Node { min_val, max_val } = seg.query(a, b);
                if min_val as usize == a && max_val as usize == b {
                    out.push_str("YES\n");
                } else {
                    out.push_str("NO\n");
                }
            } else {
                if a == b { continue; }
                let va = arr[a];
                let vb = arr[b];
                arr[a] = vb; seg.update(a, vb);
                arr[b] = va; seg.update(b, va);
            }
        }
    }
    
    print!("{out}");
}