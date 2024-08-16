#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    vector<vector<int>> graph(n, vector<int>(n, 50));
    int u, v;
    cin >> u >> v;
    while (u != -1 && v != -1) {
        graph[u - 1][v - 1] = 1;
        graph[v - 1][u - 1] = 1;
        cin >> u >> v;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                graph[j][k] = min(graph[j][k], graph[j][i] + graph[i][k]);
            }
        }
    }

    vector<int> score(n, 0);
    int minimum = 50;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) continue;
            score[i] = max(score[i], graph[i][j]);
        }
        minimum = min(minimum, score[i]);
    }

    vector<int> candidates;
    for (int i = 0; i < n; i++) {
        if (score[i] == minimum) candidates.push_back(i);
    }

    cout << minimum << ' ' << candidates.size() << '\n';
    for (int idx : candidates) cout << (idx + 1) << ' ';

    return 0;
}