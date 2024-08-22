#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<short>> graph(n, vector<short>(n, 0));
    int u, v;
    for (int i = 0; i < m; i++) {
        cin >> u >> v;
        graph[u - 1][v - 1] = 1;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (graph[j][i] * graph[i][k] > 0) {
                    graph[j][k] = 1;
                }
            }
        }
    }

    int answer = 0;
    for (int i = 0; i < n; i++) {
        int lighter = 0, heavier = 0;
        for (int j = 0; j < n; j++) {
            if (graph[i][j] == 1) heavier++;
            else if (graph[j][i] == 1) lighter++;
        }

        if (heavier > n / 2 || lighter > n / 2) answer++;
    }

    cout << answer;

    return 0;
}