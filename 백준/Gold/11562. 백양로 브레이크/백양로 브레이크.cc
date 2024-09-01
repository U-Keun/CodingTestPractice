#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP_INC(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<int>> adj(n, vector<int>(n, 250));
    REP_INC(i, 0, n - 1) adj[i][i] = 0;

    int u, v, b;
    REP_INC(i, 0, m - 1) {
        cin >> u >> v >> b;
        adj[u - 1][v - 1] = 0;
        adj[v - 1][u - 1] = (b == 1) ? 0 : 1;
    }

    REP_INC(i, 0, n - 1) {
        REP_INC(j, 0, n - 1) {
            REP_INC(k, 0, n - 1) {
                adj[j][k] = min(adj[j][k], adj[j][i] + adj[i][k]);
            }
        }
    }

    int k;
    cin >> k;

    int s, e;
    REP_INC(i, 0, k - 1) {
        cin >> s >> e;
        cout << adj[s - 1][e - 1] << '\n';
    }

    return 0;
}