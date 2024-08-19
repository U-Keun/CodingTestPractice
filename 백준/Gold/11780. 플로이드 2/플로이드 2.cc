#include <iostream>
#include <vector>
#include <deque>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<int>> graph(n, vector<int>(n, 10000001));
    vector<vector<int>> record(n, vector<int>(n, -1));

    int u, v, w;
    for (int i = 0; i < m; i++) {
        cin >> u >> v >> w;
        graph[u - 1][v - 1] = min(graph[u - 1][v - 1], w);
        record[u - 1][v - 1] = v;
    }

    for (int i = 0; i < n; i++) graph[i][i] = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (graph[j][k] > graph[j][i] + graph[i][k]) {
                    graph[j][k] = graph[j][i] + graph[i][k];
                    record[j][k] = record[j][i];
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (graph[i][j] > 10000000) {
                cout << 0 << ' ';
                continue;
            }
            cout << graph[i][j] << ' ';
        }
        cout << '\n';
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) {
                cout << 0 << '\n';
                continue;
            }

            deque<int> path;
            path.emplace_back(i + 1);
            while (record[path.back() - 1][j] >= 0) {
                path.emplace_back(record[path.back() - 1][j]);
            }

            if (path.size() == 1) {
                cout << 0 << '\n';
                continue;
            }

            cout << path.size() << ' ';
            while (!path.empty()) {
                cout << path.front() << ' ';
                path.pop_front();
            }
            cout << '\n';
        }
    }

    return 0;
}