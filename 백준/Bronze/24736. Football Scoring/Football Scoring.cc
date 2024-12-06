#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int calc(int t, int f, int s, int p, int c) {
    return t * 6 + f * 3 + s * 2 + p + c * 2;
}

int main() {
    FAST_IO

    int t, f, s, p, c, a, b;
    cin >> t >> f >> s >> p >> c;
    a = calc(t, f, s, p, c);
    cin >> t >> f >> s >> p >> c;
    b = calc(t, f, s, p, c);

    cout << a << ' ' << b;

    return EXIT_SUCCESS;
}