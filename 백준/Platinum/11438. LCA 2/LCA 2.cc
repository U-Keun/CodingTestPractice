#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

static const int MAX_N = 100000, LOG_N = 17;

vector<int> graph[MAX_N], depth(MAX_N);
int parent[MAX_N][LOG_N];

void record_depth(int cur, int par, int d) {
    depth[cur] = d;
    parent[cur][0] = par;
    for (int nbd : graph[cur]) {
        if (nbd == par) continue;
        record_depth(nbd, cur, d + 1);
    }
}

int lca(int a, int b) {
    if (depth[a] < depth[b]) swap(a, b);
    int diff = depth[a] - depth[b];
    for (int k = 0; diff; k++) {
        if (diff & 1) a = parent[a][k];
        diff >>= 1;
    }

    if (a == b) return a;

    for (int k = LOG_N - 1; k >= 0; k--) {
        if (parent[a][k] != parent[b][k]) {
            a = parent[a][k];
            b = parent[b][k];
        }
    }

    return parent[a][0];
}

int main() {
    FAST_IO

    int n; cin >> n;
    REP(i, 1, n - 1) {
        int u, v; cin >> u >> v;
        u--; v--;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    record_depth(0, -1, 0);

    REP(i, 1, LOG_N - 1) {
        REP(node, 0, n - 1) {
            int tmp = parent[node][i - 1];
            if (tmp < 0) parent[node][i] = -1;
            else parent[node][i] = parent[tmp][i - 1];
        }
    }

    int m; cin >> m;
    while (m--) {
        int a, b; cin >> a >> b;
        a--; b--;
        cout << lca(a, b) + 1 << '\n';
    }

   return EXIT_SUCCESS;
}