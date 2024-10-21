#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;

    int k = 2;
    while (k * k < n) {
        k++;
    }

    if (k == 2) {
        cout << 4;
        return 0;
    }

    if ((k - 1) * k > n) {
        cout << 4 * k - 6;
    } else {
        cout << 4 * k - 4;
    }

    return 0;
}