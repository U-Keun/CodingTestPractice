#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int a, b, c, d; cin >> a >> b >> c >> d;
    c += d % 60;
    b += d / 60;
    if (c >= 60) {
        b++;
        c -= 60;
    }
    a += b / 60;
    b %= 60;
    a %= 24;

    cout << a << ' '
        << b << ' '
        << c;

    return EXIT_SUCCESS;
}