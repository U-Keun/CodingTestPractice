#include <iostream>
#include <climits>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;

    if (n == 0) {
        cout << "NO\n";
        return 0;
    }

    int val = 1;
    while (val < INT_MAX / 3 && val <= n) {
        val *= 3;
    }

    while (n > 0 && val > 0) {
        if (n >= val) {
            n -= val;
        }
        val /= 3;
    }

    if (n == 0) cout << "YES\n";
    else cout << "NO\n";

    return 0;
}