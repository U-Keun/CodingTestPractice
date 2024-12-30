#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

bool dfs(int cur, int color, const vector<vector<int>> &graph, vector<int> &colors) {
    colors[cur] = color;

    for (auto &nbd : graph[cur]) {
        if (colors[nbd] == -1) {
            if (!dfs(nbd, 1 - color, graph, colors)) return false;
        } else if (colors[cur] == colors[nbd]) return false;
    }

    return true;
}

bool is_bipartite(const vector<vector<int>> &graph) {
    int n = graph.size();
    vector<int> colors(n, -1);

    REP(i, 0, n - 1) {
        if (colors[i] == -1) {
            if (!dfs(i, 0, graph, colors)) return false;
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
        int u, w;
        while (e-- > 0) {
            cin >> u >> w;
            graph[u - 1].emplace_back(w - 1);
            graph[w - 1].emplace_back(u - 1);
        }

        if (is_bipartite(graph)) cout << "YES\n";
        else cout << "NO\n";
    }

    return EXIT_SUCCESS;
}