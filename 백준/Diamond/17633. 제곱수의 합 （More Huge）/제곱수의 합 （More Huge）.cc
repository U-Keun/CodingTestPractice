#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <random>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long

vector<ll> test_bases = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };
unordered_map<ll, bool> factors;
ll num, common_factor;

ll power_mod(ll a, ll b, ll mod) {
    ll res = 1;
    while (b) {
        if (b % 2) res = (__int128) res * a % mod;
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
        if (d == n) return pollard_rho(n);
    }

    return d;
}

void factorize(ll n) {
    if (n == 1) return;
    if (is_prime(n)) {
        factors[n] ^= 1;
        if (!factors[n]) {
            num /= n * n;
            common_factor *= n;
        }
        return;
    }
    ll divisor = pollard_rho(n);
    factorize(divisor);
    factorize(n / divisor);
}

int main() {
    FAST_IO

    cin >> num;

    ll rt = (ll) floorl(sqrtl((long double)num));
    if(rt * rt == num) {
        cout << 1;
        return EXIT_SUCCESS;
    }

    common_factor = 1;
    factorize(num);

    if(num % 8 == 7) {
        cout << 4;
        return EXIT_SUCCESS;
    }

    for (auto f : factors) {
        if (!f.second) continue;
        if ((f.first % 4) == 3) {
            cout << 3;
            return EXIT_SUCCESS;
        }
    }

    cout << 2;

    return EXIT_SUCCESS;
}