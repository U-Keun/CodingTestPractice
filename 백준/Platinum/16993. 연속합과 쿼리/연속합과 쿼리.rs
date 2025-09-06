use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone, Copy, Debug, Default)]
struct Node {
    sum: i64,
    pref: i64,
    suff: i64,
    best: i64,
}

#[inline]
fn merge(a: Node, b: Node) -> Node {
    Node {
        sum: a.sum + b.sum,
        pref: a.pref.max(a.sum + b.pref),
        suff: b.suff.max(b.sum + a.suff),
        best: a.best.max(b.best).max(a.suff + b.pref),
    }
}

struct SegTree {
    n: usize,
    size: usize,
    tree: Vec<Node>,
}
impl SegTree {
    fn new(a: &[i64]) -> Self {
        let n = a.len();
        let mut size = 1usize;
        while size < n { size <<= 1 }
        let mut tree = vec![Node::default(); size << 1];
        
        for i in 0..n {
            let v = a[i];
            tree[size + i] = Node { sum: v, pref: v, suff: v, best: v };
        }

        for i in (1..size).rev() {
            tree[i] = merge(tree[i << 1], tree[i << 1 | 1]);
        }
        Self { n, size, tree }
    }

    fn query(&self, l: usize, r: usize) -> Node {
        self.query_rec(1, 0, self.size - 1, l, r).unwrap()
    }
    
    fn query_rec(&self, idx: usize, nl: usize, nr: usize, l: usize, r: usize) -> Option<Node> {
        if r < nl || nr < l { return None; }
        if l <= nl && nr <= r {
            return Some(self.tree[idx]);
        }

        let mid = (nl + nr) / 2;
        let left = self.query_rec(idx << 1, nl, mid, l, r);
        let right = self.query_rec(idx << 1 | 1, mid + 1, nr, l, r);
        match (left, right) {
            (Some(a), Some(b)) => Some(merge(a, b)),
            (Some(a), None) => Some(a),
            (None, Some(b)) => Some(b),
            (None, None) => None,
        }
    }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut a = vec![0i64; n];
    for i in 0..n {
        a[i] = next!(it, i64);
    }

    let seg = SegTree::new(&a);

    let m = next!(it, usize);
    let mut out = String::new();
    for _ in 0..m {
        let l = next!(it, usize);
        let r = next!(it, usize);
        let answer = seg.query(l - 1, r - 1).best;
        out.push_str(&format!("{}\n", answer));
    }
    print!("{}", out);
}