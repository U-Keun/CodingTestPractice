#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int a, b, c, d, e, f, g;
    cin >> a >> b
        >> c >> d
        >> e >> f >> g;

    cout << (long long) (a * b + c * d) * e * f * g << '\n';

    return 0;
}