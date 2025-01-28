#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define LOG_N 19

int main() {
    FAST_IO

    int m; cin >> m;
    vector<vector<int>> results(m, vector<int>(LOG_N));
    REP(i, 0, m - 1) cin >> results[i][0];

    REP(j, 1, LOG_N - 1) {
        REP(i, 0, m - 1) {
            results[i][j] = results[results[i][j - 1] - 1][j - 1];
        }
    }

    int q; cin >> q;
    while (q--) {
        int n, x; cin >> n >> x;
        REP(i, 0, LOG_N - 1) {
            if (n & (1 << i)) {
                x = results[x - 1][i];
            }
        }
        cout << x <<  '\n';
    }

    return EXIT_SUCCESS;
}