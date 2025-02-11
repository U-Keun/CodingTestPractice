#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<vector<int>> graph;
vector<int> score, add;

void dfs(int node) {
    score[node] += add[node];
    for (const auto &child : graph[node]) {
        add[child] += add[node];
        dfs(child);
    }
}

int main() {
    FAST_IO

    int n, m; cin >> n >> m;
    graph.resize(n);
    REP(i, 0, n - 1) {
        int p; cin >> p;
        if (i == 0) continue;
        p--; // zero-base
        graph[p].push_back(i);
    }

    add.resize(n);
    while (m--) {
        int i, w; cin >> i >> w;
        add[i - 1] += w;
    }

    score.resize(n);
    dfs(0);

    REP(i, 0, n - 1) cout << score[i] << ' ';

    return EXIT_SUCCESS;
}