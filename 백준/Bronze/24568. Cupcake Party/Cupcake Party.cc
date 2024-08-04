#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int r, s;
    cin >> r >> s;

    int ans = r * 8 + s * 3 - 28;
    if (ans >= 0) cout << ans << '\n';
    else cout << 0 << '\n';

    return 0;
}