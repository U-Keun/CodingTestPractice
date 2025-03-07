#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long

vector<ll> test_bases = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };

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

int main() {
    FAST_IO

    ll a, b; cin >> a >> b;
    if (a % 2 == 0) a++;
    if (b % 2 == 0) b--;
    vector<ll> answer;
    for (ll i = a; i <= b; i += 2) {
        if (i == 9) answer.push_back(9);
        else if (is_prime(i)) answer.push_back(i);
    }

    for (ll num : answer) cout << num << ' ';
    return EXIT_SUCCESS;
}