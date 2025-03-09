#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <random>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long
#define REP(i, a, b) for (int i = a; i <= b; i++)
#define BASE 1000000000

vector<ll> test_bases = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };
unordered_map<ll, int> factors;
vector<int> freq;

struct big_int {
    int size;
    vector<ll> number;

    big_int(ll v = 0) {
        size = 0;
        if (v == 0) {
            number.push_back(0);
            size = 1;
        }
        while (v) {
            number.push_back(v % BASE);
            v /= BASE;
            size++;
        }
    }

    big_int operator * (const big_int &other) const {
        big_int res;
        res.number.resize(this->size + other.size);
        ll over;
        REP(i, 0, this->size - 1) {
            over = 0;
            REP(j, 0, other.size - 1) {
                ll val = res.number[i + j] + number[i] * other.number[j] + over;
                res.number[i + j] = val % BASE;
                over = val / BASE;
            }

            int idx = other.size;
            while (over) {
                ll val = res.number[i + idx] + over;
                res.number[i + idx] = val % BASE;
                over = val / BASE;
                idx++;
            }
        }

        while (!res.number.empty() && res.number.back() == 0)
            res.number.pop_back();
        res.size = res.number.size();
        return res;
    }

    static big_int power(big_int base, long long exp) {
        big_int res(1);
        while(exp > 0) {
            if (exp & 1) res = res * base;
            base = base * base;
            exp /= 2;
        }
        return res;
    }

    string num_string() const {
        if(number.empty()) return "0";
        string s = to_string(number.back());
        for (int i = size - 2; i >= 0; i--) {
            string part = to_string(number[i]);
            s += string(9 - part.size(), '0') + part;
        }
        return s;
    }
};

ll power_mod(ll a, ll b, ll mod) {
    ll res = 1;
    while (b) {
        if (b & 1) res = (__int128) res * a % mod;
        a = (__int128) a * a % mod;
        b /= 2;
    }
    return res;
}

bool miller_rabin(ll n, ll a) {
    if (n % a == 0) return false;
    ll d = n - 1;
    while (d % 2 == 0) d /= 2;

    ll x = power_mod(a, d, n);
    if (x == 1 || x == n - 1) return true;

    while (d != n - 1) {
        x = (__int128) x * x % n;
        d *= 2;
        if (x == n - 1) return true;
        if (x == 1) return false;
    }
    return false;
}

bool is_prime(ll n) {
    if (n < 2) return false;
    if (n % 2 == 0) return (n == 2);

    for (ll a : test_bases) {
        if (a >= n) break;
        if (!miller_rabin(n, a)) return false;
    }
    return true;
}

ll gcd(ll a, ll b) {
    while (b) {
        a %= b;
        swap(a, b);
    }
    return a;
}

ll pollard_rho(ll n) {
    if (n % 2 == 0) return 2;

    random_device rd;
    mt19937_64 gen(rd());
    uniform_int_distribution<ll> dist(2, n - 1);

    ll x = dist(gen), y = x, c = dist(gen);
    ll d = 1;

    auto f = [&](ll v) { return ((__int128)v * v + c) % n; };

    while (d == 1) {
        x = f(x);
        y = f(f(y));
        d = gcd(abs(x - y), n);
    }

    return d;
}

void factorize(ll n) {
    if (n == 1) return;
    if (is_prime(n)) {
        factors[n]++;
        return;
    }
    ll divisor = pollard_rho(n);
    factorize(divisor);
    factorize(n / divisor);
}

int main() {
    FAST_IO

    int n; cin >> n;
    ll d;
    while (n--) {
        cin >> d;
        factorize(d);
    }

    freq.resize(40001, 0);
    for (auto &f : factors) {
        freq[f.second]++;
    }

    for (int i = 40000; i >= 1; i--) {
        if (freq[i] == 0) continue;
        big_int f = big_int::power(big_int(2), freq[i]);
        int idx = 0;
        while (idx < f.size) {
            if (f.number[idx] > 0) {
                f.number[idx]--;
                break;
            } else {
                f.number[idx] = BASE - 1;
                idx++;
            }
        }

        while (!f.number.empty() && f.number.back() == 0)
            f.number.pop_back();
        cout << i << '\n' << f.num_string();
        return EXIT_SUCCESS;
    }

    return EXIT_SUCCESS;
}