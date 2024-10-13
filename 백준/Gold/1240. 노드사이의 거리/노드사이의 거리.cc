#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<int>> record(n, vector<int>(n, 1000000000));
    REP(i, 0, n - 1) record[i][i] = 0;

    int u, v, w;
    REP(i, 0, n - 2) {
        cin >> u >> v >> w;
        record[u - 1][v - 1] = w;
        record[v - 1][u - 1] = w;
    }

    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            REP(k, 0, n - 1) {
                record[j][k] = min(record[j][k], record[j][i] + record[i][k]);
            }
        }
    }

    int from, to;
    while (m-- > 0) {
        cin >> from >> to;
        cout << record[from - 1][to - 1] << '\n';
    }

    return 0;
}