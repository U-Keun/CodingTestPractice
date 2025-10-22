use std::io::{ self, Read };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let n = next!(it, usize);
    let mut inord = vec![0usize; n];
    for i in 0..n { inord[i] = next!(it, usize); }
    let mut postord = vec![0usize; n];
    for i in 0..n { postord[i] = next!(it, usize); }

    let mut idx = vec![0usize; n + 1];
    for (i, &v) in inord.iter().enumerate() { idx[v] = i; }

    let mut ans: Vec<usize> = Vec::with_capacity(n);
    let mut st: Vec<(usize, usize, usize, usize)> = Vec::with_capacity(n);
    st.push((0, n - 1, 0, n - 1));

    while let Some((is, ie, ps, pe)) = st.pop() {
        if is > ie || ps > pe { continue; }
        let root = postord[pe];
        ans.push(root);

        let k = idx[root];
        let lsz = if k >= is { k - is } else { 0 };
        let rsz = if ie >= k { ie - k } else { 0 };

        if rsz > 0 {
            st.push((k + 1, ie, ps + lsz, pe - 1));
        }
        if lsz > 0 {
            st.push((is, k - 1, ps, ps + lsz - 1));
        }
    }

    let mut out = String::new();
    for (i, v) in ans.iter().enumerate() {
        if i > 0 { out.push(' '); }
        out.push_str(&v.to_string());
    }
    println!("{out}");
}