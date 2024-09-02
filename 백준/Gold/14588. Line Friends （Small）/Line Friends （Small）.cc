#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP_INC(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;
    vector<pair<int, int>> lines(n);

    REP_INC(i, 0, n - 1) {
        cin >> lines[i].first >> lines[i].second;
    }

    vector<vector<int>> adj(n, vector<int>(n, 300));
    REP_INC(i, 0, n - 1) adj[i][i] = 0;
    REP_INC(i, 0, n - 2) {
        REP_INC(j, i + 1, n - 1) {
            if ((lines[i].second >= lines[j].first && lines[i].first <= lines[j].second) ||
                (lines[j].second >= lines[i].first && lines[j].first <= lines[i].second)) {
                adj[i][j] = 1;
                adj[j][i] = 1;
            }
        }
    }

    REP_INC(i, 0, n - 1) {
        REP_INC(j, 0, n - 1) {
            REP_INC(k, 0, n - 1) {
                adj[j][k] = min(adj[j][k], adj[j][i] + adj[i][k]);
            }
        }
    }

    int q;
    cin >> q;
    int u, v;
    REP_INC(i, 0, q - 1) {
        cin >> u >> v;
        if (adj[u - 1][v - 1] == 300) cout << "-1\n";
        else cout << adj[u - 1][v - 1] << '\n';
    }

    return 0;
}