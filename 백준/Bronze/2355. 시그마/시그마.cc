#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    long long a, b; cin >> a >> b;
    if (a < b) swap(a, b);

    cout << (a + b) * (a - b + 1) / 2;

    return EXIT_SUCCESS;
}