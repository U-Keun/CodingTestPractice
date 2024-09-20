#include <iostream>
#include <iomanip>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    double d, f, p;
    REP(i, 1, n) {
        cin >> d >> f >> p;
        cout << "$" << fixed << setprecision(2) << (d * f * p) << '\n';
    }

    return 0;
}