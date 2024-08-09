#include <iostream>
#include <iomanip>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int a, b;
    while (cin >> a >> b) {
        double result = static_cast<double>(a) / b;
        cout << fixed << setprecision(2) << result << '\n';
    }

    return 0;
}