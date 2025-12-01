#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int s, d, i, l, n;
    cin >> s >> d >> i >> l >> n;

    int answer = n * 4 - (s + d + i + l);
    if (answer < 0) cout << 0 << '\n';
    else cout << answer << '\n';

    return EXIT_SUCCESS;
}