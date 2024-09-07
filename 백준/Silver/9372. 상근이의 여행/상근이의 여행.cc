#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP_INC(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int t;
    cin >> t;

    int n, m;
    REP_INC(i, 0, t - 1) {
        cin >> n >> m;
        int u, v;
        REP_INC(j, 0, m - 1) {
            cin >> u >> v;
        }

        cout << n - 1 << '\n';
    }

    return 0;
}