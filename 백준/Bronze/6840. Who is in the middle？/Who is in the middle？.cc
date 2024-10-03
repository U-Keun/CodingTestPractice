#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int a, b, c;
    cin >> a >> b >> c;

    if (a > b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    if (b < c) cout << b;
    else if (c > a) cout << c;
    else cout << a;

    return 0;
}