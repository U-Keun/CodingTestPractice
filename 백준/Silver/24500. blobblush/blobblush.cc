#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define ll long long

int main() {
    FAST_IO

    ll n;
    cin >> n;

    ll first = 0, second = 0, digit = 1;
    bool firstContainsZero = false;
    while (n > 0) {
        int tmp = n % 2;
        n /= 2;
        if (tmp == 1) first |= digit;
        else {
            if (!firstContainsZero) firstContainsZero = true;
            second |= digit;
        }
        digit <<= 1;
    }

    if (firstContainsZero) {
        cout << 2 << '\n'
            << second << '\n'
            << first << '\n';
    } else {
        cout << 1 << '\n'
            << first << '\n';
    }

    return 0;
}