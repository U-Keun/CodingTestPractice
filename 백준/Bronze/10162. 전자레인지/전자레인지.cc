#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int t; cin >> t;
    int a, b, c;
    a = t / 300; t %= 300;
    b = t / 60; t %= 60;
    c = t / 10; t %= 10;

    if (!t) cout << a << ' ' << b << ' ' << c << '\n';
    else cout << -1 << '\n';

    return EXIT_SUCCESS;
}