#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<vector<pair<int, int>>> graph;
vector<int> depths, distances, parents;

void record_depth(int cur, int par, int depth, int weight) {
    depths[cur] = depth;

    parents[cur] = par;
    distances[cur] = weight;


    for (auto &nbd : graph[cur]) {
        if (depths[nbd.first] != -1) continue;
        record_depth(nbd.first, cur, depth + 1, weight + nbd.second);
    }
}

int main() {
    FAST_IO

    int n; cin >> n;
    graph.resize(n);
    depths.resize(n, -1);
    distances.resize(n);
    parents.resize(n);
    REP(i, 1, n - 1) {
        int u, v, w; cin >> u >> v >> w;
        u--; v--;
        graph[u].push_back({ v, w });
        graph[v].push_back({ u, w });
    }

    record_depth(0, -1, 0, 0);

    int m; cin >> m;
    while (m--) {
        int a, b; cin >> a >> b;
        a--; b--;
        int answer = distances[a] + distances[b];
        if (depths[a] < depths[b]) swap(a, b);
        while (depths[a] != depths[b]) {
            a = parents[a];
        }

        while (a != b) {
            a = parents[a];
            b = parents[b];
        }

        answer -= 2 * distances[a];

        cout << answer << '\n';
    }

   return EXIT_SUCCESS;
}