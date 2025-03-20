#include <iostream>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int a, b, c; cin >> a >> b >> c;
    int burger = min(a, min(b, c));
    int d, e; cin >> d >> e;
    int bev = min(d, e);
    cout << burger + bev - 50 << '\n';

    return EXIT_SUCCESS;
}