use std::io::{ self, Read };
use std::collections::VecDeque;

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Clone)]
struct Node {
    next: [i32; 26],
    fail: usize,
    out: bool,
}

impl Node {
    fn new() -> Self { Self { next: [-1; 26], fail: 0, out: false, } }
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);

    let mut nodes: Vec<Node> = Vec::new();
    nodes.push(Node::new());

    for _ in 0..n {
        let s = next!(it, String);
        let mut cur = 0usize;
        for &b in s.as_bytes() {
            let c = (b - b'a') as usize;
            let next = nodes[cur].next[c];
            if next == -1 {
                nodes.push(Node::new());
                let ni = (nodes.len() - 1) as i32;
                nodes[cur].next[c] = ni;
                cur = ni as usize;
            } else {
                cur = next as usize;
            }
        }
        nodes[cur].out = true;
    }

    let mut que = VecDeque::new();
    for c in 0..26 {
        let t = nodes[0].next[c];
        if t != -1 {
            let u = t as usize;
            nodes[u].fail = 0;
            que.push_back(u);
        } else {
            nodes[0].next[c] = 0;
        }
    }

    while let Some(v) = que.pop_front() {
        for c in 0..26 {
            let t = nodes[v].next[c];
            if t != -1 {
                let u = t as usize;
                let f = nodes[v].fail;
                let uf = nodes[f].next[c] as usize;
                nodes[u].fail = uf;
                nodes[u].out |= nodes[uf].out;
                que.push_back(u);
            } else {
                let f = nodes[v].fail;
                nodes[v].next[c] = nodes[f].next[c];
            }
        }
    }
    
    let q = next!(it, usize);
    let mut out = String::new();

    for _ in 0..q {
        let s = next!(it, String);
        let mut state = 0usize;
        let mut found = false;

        for &b in s.as_bytes() {
            let c = (b - b'a') as usize;
            state = nodes[state].next[c] as usize;
            if nodes[state].out {
                found = true;
                break;
            }
        }

        if found { out.push_str("YES\n"); }
        else { out.push_str("NO\n"); }
    }

    print!("{out}");
}