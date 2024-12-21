#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long

ll gcd(ll a, ll b) {
    while (b != 0) {
        ll tmp = b;
        b = a % b;
        a = tmp;
    }
    return a;
}

ll lcm(ll a, ll b) {
    return (a / gcd(a, b) * b);
}

int main() {
    FAST_IO

    int n; cin >> n;
    ll num, answer = -1;
    while (n-- > 0) {
        cin >> num;
        if (answer < 0) {
            answer = num;
            continue;
        }

        answer = lcm(answer, num);
    }

    cout << answer * 2;

    return EXIT_SUCCESS;
}