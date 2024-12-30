#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

bool is_bipartite(vector<vector<int>> &graph) {
    int n = graph.size();
    vector<int> color(n, -1);

    REP(i, 0, n - 1) {
        if (color[i] != -1) continue;

        queue<int> q;
        q.push(i);
        color[i] = 0;

        while (!q.empty()) {
            int tmp = q.front(); q.pop();

            for (auto &nbd : graph[tmp]) {
                if (color[nbd] == -1) {
                    color[nbd] = 1 - color[tmp];
                    q.push(nbd);
                } else if (color[nbd] == color[tmp]) return false;
            }
        }
    }

    return true;
}

int main() {
    FAST_IO

    int k; cin >> k;
    int v, e;
    while (k-- > 0) {
        cin >> v >> e;
        vector<vector<int>> graph(v);
        int u, v;
        while (e-- > 0) {
            cin >> u >> v;
            graph[u - 1].emplace_back(v - 1);
            graph[v - 1].emplace_back(u - 1);
        }

        if (is_bipartite(graph)) cout << "YES\n";
        else cout << "NO\n";
    }

    return EXIT_SUCCESS;
}