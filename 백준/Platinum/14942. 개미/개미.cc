#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define MAX_N 100000
#define LOG_N 17

vector<int> ants;
vector<vector<pair<int, int>>> graph;
int parent[MAX_N][LOG_N];
long long weights[MAX_N][LOG_N];

void update_parent(int cur, int par, int weight) {
    parent[cur][0] = par;
    weights[cur][0] = weight;

    for (auto &nbd : graph[cur]) {
        if (nbd.first != par) {
            update_parent(nbd.first, cur, nbd.second);
        }
    }
}

int main() {
    FAST_IO

    int n; cin >> n;
    graph.resize(n);
    ants.resize(n);
    REP(i, 0, n - 1) cin >> ants[i];

    REP(i, 1, n - 1) {
        int u, v, w; cin >> u >> v >> w;
        u--; v--;
        graph[u].push_back({ v, w });
        graph[v].push_back({ u, w });
    }

    update_parent(0, -1, 0);

    REP(i, 1, LOG_N - 1) {
        REP(node, 0, n - 1) {
            int tmp = parent[node][i - 1];
            if (tmp == -1) {
                parent[node][i] = -1;
                weights[node][i] = 0;
            } else {
                parent[node][i] = parent[tmp][i - 1];
                weights[node][i] = weights[node][i - 1] + weights[tmp][i - 1];
            }
        }
    }

    cout << "1\n";
    REP(node, 1, n - 1) {
        int cur = node, energy = ants[cur], k = LOG_N - 1;
        while (cur) {
            while (k >= 0 && (parent[cur][k] < 0 || weights[cur][k] > energy)) k--;
            if (k < 0) break;
            energy -= weights[cur][k];
            cur = parent[cur][k];
        }
        cout << cur + 1 << '\n';
    }



    return EXIT_SUCCESS;
}