#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

vector<vector<int>> graph;
int answer = 0;

int dfs(int prev, int cur) {
    if (prev >= 0 && graph[cur].size() == 1) return 1;

    int decider = 0;
    for (int nbd : graph[cur]) {
        if (nbd == prev) continue;
        decider += dfs(cur, nbd);
    }

    if (decider > 0) {
        answer++;
        return 0;
    }

    return 1;
}

int main() {
    FAST_IO

    int n; cin >> n;
    graph.resize(n);
    for (int i = 0; i < n - 1; i++) {
        int u, v; cin >> u >> v;
        graph[u - 1].push_back(v - 1);
        graph[v - 1].push_back(u - 1);
    }

    dfs(-1, 0);

    cout << answer;

    return EXIT_SUCCESS;
}