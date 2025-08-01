use std::io::{ self, Read };
use std::collections::{ HashMap, BTreeSet };

macro_rules! next {
    ($it: expr, $t: ty) => {
        $it.next().unwrap().parse::<$t>().unwrap()
    };
}

#[derive(Debug, Clone, Copy, Eq, PartialEq, Hash)]
struct Card { suit: char, rank: u8 }

fn parse_card(tok: &str) -> Card {
    let suit = tok.chars().last().unwrap();
    let rank: u8 = tok[..tok.len() - 1].parse().unwrap();
    Card { suit, rank }
}

fn check(cards: &[Card]) -> bool {
    let mut freq: HashMap<(char, u8), usize> = HashMap::new();
    let mut by_suit: HashMap<char, BTreeSet<u8>> = HashMap::new();

    for &Card { suit, rank } in cards {
        *freq.entry((suit, rank)).or_default() += 1;
        by_suit.entry(suit).or_default().insert(rank);
    }

    let triple_same = freq.values().any(|&c| c >= 3);
    let all_pairs = freq.values().all(|&c| c % 2 == 0);
    let seq_three = by_suit.values().any(|set| {
        let nums: Vec<_> = set.iter().copied().collect();
        nums.windows(3).any(|w| w[0] + 1 == w[1] && w[1] + 1 == w[2])
    });

    triple_same || all_pairs || seq_three
}

fn main() {
    let mut buf = String::new();
    io::stdin().read_to_string(&mut buf).unwrap();
    let mut it = buf.split_whitespace();

    let t: usize = next!(it, usize);

    for _ in 0..t {
        let mut cards = Vec::with_capacity(4);
        for _ in 0..4 {
            cards.push(parse_card(it.next().unwrap()));
        }

        let res = check(&cards);

        if res { println!(":)"); }
        else { println!(":("); }
    }
}