#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;

    int a, b, x;
    while (n-- > 0) {
        cin >> a >> b >> x;
        cout << a * (x - 1) + b << '\n';
    }

    return EXIT_SUCCESS;
}