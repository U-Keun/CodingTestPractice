#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

#define MAX_N 100000
#define LOG_N 17
#define INF 1000001

vector<vector<pair<int, int>>> graph;
vector<int> depths;
int parent[MAX_N][LOG_N], min_edge[MAX_N][LOG_N], max_edge[MAX_N][LOG_N];

void record_depth(int cur, int par, int weight, int depth) {
    depths[cur] = depth;
    parent[cur][0] = par;
    if (par == -1) {
        min_edge[cur][0] = INF;
        max_edge[cur][0] = 0;
    } else {
        min_edge[cur][0] = weight;
        max_edge[cur][0] = weight;
    }

    for (auto &nbd : graph[cur]) {
        if (nbd.first != par) record_depth(nbd.first, cur, nbd.second, depth + 1);
    }
}

int main() {
    FAST_IO

    int n; cin >> n;
    graph.resize(n);
    depths.resize(n, -1);

    REP(i, 1, n - 1) {
        int u, v, w; cin >> u >> v >> w;
        u--; v--;
        graph[u].push_back({ v, w });
        graph[v].push_back({ u, w });
    }

    record_depth(0, -1, 0, 0);

    REP(i, 1, LOG_N - 1) {
        REP(node, 0, n - 1) {
            int tmp = parent[node][i - 1];
            if (tmp < 0) {
                parent[node][i] = -1;
                min_edge[node][i] = min_edge[node][i - 1];
                max_edge[node][i] = max_edge[node][i - 1];
            } else {
                parent[node][i] = parent[tmp][i - 1];
                min_edge[node][i] = min(min_edge[node][i - 1], min_edge[tmp][i - 1]);
                max_edge[node][i] = max(max_edge[node][i - 1], max_edge[tmp][i - 1]);
            }

        }
    }

    int k; cin >> k;
    while (k--) {
        int d, e; cin >> d >> e;
        d--; e--;
        int maximum = 0, minimum = 1000001;
        if (depths[d] < depths[e]) swap(d, e);
        int diff = depths[d] - depths[e], l = 0;
        while (diff) {
            if (diff & 1) {
                minimum = min(minimum, min_edge[d][l]);
                maximum = max(maximum, max_edge[d][l]);
                d = parent[d][l];
            }
            l++;
            diff >>= 1;
        }

        if (d != e) {
            for (l = LOG_N - 1; l >= 0; l--) {
                if (parent[d][l] != parent[e][l]) {
                    minimum = min(minimum, min_edge[d][l]);
                    maximum = max(maximum, max_edge[d][l]);
                    minimum = min(minimum, min_edge[e][l]);
                    maximum = max(maximum, max_edge[e][l]);
                    d = parent[d][l];
                    e = parent[e][l];
                }
            }

            minimum = min(minimum, min_edge[d][0]);
            maximum = max(maximum, max_edge[d][0]);
            minimum = min(minimum, min_edge[e][0]);
            maximum = max(maximum, max_edge[e][0]);
            d = parent[d][0];
            e = parent[e][0];
        }

        cout << minimum << ' ' << maximum << '\n';
    }

    return EXIT_SUCCESS;
}