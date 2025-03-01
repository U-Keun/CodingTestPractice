#include <iostream>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int l, a, b, c, d;
    cin >> l >> a >> b >> c >> d;

    int k = a / c + (a % c ? 1 : 0),
        m = b / d + (b % d ? 1 : 0);

    cout << (l - max(k, m));

    return EXIT_SUCCESS;
}