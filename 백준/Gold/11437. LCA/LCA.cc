#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<vector<int>> graph;
vector<int> depthRecord, parent;

void recordDepth(int cur, int depth) {
    depthRecord[cur] = depth;

    for (auto& nbd : graph[cur]) {
        if (depthRecord[nbd] != -1) continue;
        parent[nbd] = cur;
        recordDepth(nbd, depth + 1);
    }
}

int main() {
    FAST_IO

    int n;
    cin >> n;
    graph.resize(n);
    parent.resize(n, -1);
    depthRecord.resize(n, -1);

    int u, v;
    REP(i, 0, n - 2) {
        cin >> u >> v;
        graph[u - 1].push_back(v - 1);
        graph[v - 1].push_back(u - 1);
    }

    recordDepth(0, 0);

    int m, a, b;
    cin >> m;
    while (m-- > 0) {
        cin >> a >> b;
        a--; b--;
        while (depthRecord[a] != depthRecord[b]) {
            if (depthRecord[a] < depthRecord[b]) b = parent[b];
            else a = parent[a];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        cout << (a + 1) << '\n';
    }

    return 0;
}