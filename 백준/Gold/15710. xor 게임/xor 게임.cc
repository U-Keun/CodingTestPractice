#include <iostream>
#define MOD 1000000007
#define ll long long

using namespace std;

long long modPower(long long base, long long exp) {
    base %= MOD;
    long long value = 1;
    while (exp) {
        if (exp % 2) {
            value = (value * base) % MOD;
        }
        base = (base * base) % MOD;
        exp /= 2;
    }
    return value;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    ll a, b, n;
    cin >> a >> b >> n;

    cout << modPower(2, 31 * (n - 1)) << '\n';

    return 0;
}