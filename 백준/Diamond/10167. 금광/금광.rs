use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone)]
struct Mine { x: i64, y: i64, w: i64 }

#[derive(Clone, Copy, Debug, Default)]
struct Node {
    sum: i64,
    left: i64,
    right: i64,
    best: i64,
}

fn merge(a: Node, b: Node) -> Node {
    let sum = a.sum + b.sum;
    let left = a.left.max(a.sum + b.left);
    let right = b.right.max(a.right + b.sum);
    let best = a.best.max(b.best).max(a.right + b.left);
    Node { sum, left, right, best }
}

struct SegTree {
    n: usize,
    size: usize,
    tree: Vec<Node>,
}
impl SegTree {
    fn new(n: usize) -> Self {
        let mut size = 1usize;
        while size < n { size <<= 1; }
        SegTree { n, size, tree: vec![Node::default(); size << 1] }
    }
    fn clear(&mut self) {
        self.tree.fill(Node::default());
    }
    fn add(&mut self, mut idx: usize, v: i64) {
        idx += self.size;
        let cur = self.tree[idx].sum + v;
        let b = cur.max(0);
        self.tree[idx] = Node { sum: cur, left: b, right: b, best: b };
        idx >>= 1;
        while idx > 0 {
            self.tree[idx] = merge(self.tree[idx << 1], self.tree[idx << 1 | 1]);
            idx >>= 1;
        }
    }
    fn best(&self) -> i64 { self.tree[1].best }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut mines: Vec<Mine> = Vec::with_capacity(n);
    let mut xs: Vec<i64> = Vec::with_capacity(n);
    let mut ys: Vec<i64> = Vec::with_capacity(n);

    for _ in 0..n {
        let x = next!(it, i64);
        let y = next!(it, i64);
        let w = next!(it, i64);
        mines.push(Mine { x, y, w });
        xs.push(x);
        ys.push(y);
    }

    xs.sort_unstable(); xs.dedup();
    ys.sort_unstable(); ys.dedup();

    for m in &mut mines {
        m.x = xs.binary_search(&m.x).unwrap() as i64;
        m.y = ys.binary_search(&m.y).unwrap() as i64;
    }

    let ycnt = ys.len();
    let xcnt = xs.len();
    let mut by_y: Vec<Vec<(usize, i64)>> = vec![Vec::new(); ycnt];
    for m in &mines {
        by_y[m.y as usize].push((m.x as usize, m.w));
    }

    let mut seg = SegTree::new(xcnt);
    let mut answer = 0i64;

    for y1 in 0..ycnt {
        seg.clear();

        for y2 in y1..ycnt {
            for &(xi, w) in &by_y[y2] {
                seg.add(xi, w);
            }
            answer = answer.max(seg.best());
        }
    }

    println!("{answer}");
}