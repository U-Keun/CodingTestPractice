#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int n, k, groups = 0;

void rec(int num) {
    if ((num - k) % 2 == 1) {
        groups++;
        return;
    }
    int left = (num - k) / 2;
    if (left > 0 && num - left > 0) {
        rec(left);
        rec(num - left);
    } else groups++;
}

int main() {
    FAST_IO;

    cin >> n >> k;
    rec(n);

    cout << groups << '\n';

    return EXIT_SUCCESS;
}