#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<int>> adj(n, vector<int>(n, n));

    int u, v;
    for (int i = 0; i < m; i++) {
        cin >> u >> v;
        adj[u - 1][v - 1] = 1;
        adj[v - 1][u - 1] = 1;
    }

    for (int i = 0; i < n; i++) adj[i][i] = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                adj[j][k] = min(adj[j][k], adj[j][i] + adj[i][k]);
            }
        }
    }

    vector<bool> mark(n, false);
    vector<pair<int, int>> representative(n, { 0, n });

    int groupCount = 0;
    for (int i = 0; i < n; i++) {
        if (!mark[i]) {
            mark[i] = true;
            groupCount++;
        }
        int val = 0;
        vector<int> group;
        for (int j = 0; j < n; j++) {
            if (adj[i][j] < n) {
                if (!mark[j]) mark[j] = true;
                group.emplace_back(j);
                val = max(val, adj[i][j]);
            }
        }

        if (val < representative[i].second) {
            for (int idx : group) {
                representative[idx].first = i;
                representative[idx].second = val;
            }
        }
    }
    cout << groupCount << '\n';

    int prev = 0;
    priority_queue<int> sort;
    for (auto pair : representative) {
        if (prev == pair.first) continue;
        sort.push(- pair.first);
        prev = pair.first;
    }

    while (!sort.empty()) {
        cout << ( - sort.top() + 1) << '\n';
        sort.pop();
    }

    return 0;
}