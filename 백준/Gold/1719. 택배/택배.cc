#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;
    vector<vector<int>> graph(n, vector<int>(n, 200001));
    vector<vector<int>> record(n, vector<int>(n, -1));
    int u, v, w;
    for (int i = 0; i < m; i++) {
        cin >> u >> v >> w;
        graph[u - 1][v - 1] = w;
        graph[v - 1][u - 1] = w;
        record[u - 1][v - 1] = v;
        record[v - 1][u - 1] = u;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                int val = graph[j][i] + graph[i][k];
                if (graph[j][k] > val) {
                    graph[j][k] = val;
                    record[j][k] = record[j][i];
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j || record[i][j] < 0) {
                cout << "- ";
                continue;
            }
            cout << record[i][j] << ' ';
        }
        cout << '\n';
    }

    return 0;
}