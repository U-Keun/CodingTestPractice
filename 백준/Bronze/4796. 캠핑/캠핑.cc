#include <iostream>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int l, p, v; cin >> l >> p >> v;
    int idx = 1;
    while (l && p && v) {
        int ans = l * (v / p);
        ans += min(l, v % p);

        cout << "Case " << idx << ": " << ans << '\n';
        idx++;
        cin >> l >> p >> v;
    }

    return EXIT_SUCCESS;
}