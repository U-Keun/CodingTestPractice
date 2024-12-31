#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n; cin >> n;
    REP(i, 1, n) {
        REP(j, 1, n - i) cout << ' ';
        REP(j, 1, 2 * i - 1) cout << '*';
        cout << '\n';
    }

    return EXIT_SUCCESS;
}