#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n, k;
    cin >> n >> k;
    vector<vector<bool>> graph(n, vector<bool>(n, false));
    int u, v;
    for (int i = 0; i < k; i++) {
        cin >> u >> v;
        graph[u - 1][v - 1] = true;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (graph[j][k]) continue;
                graph[j][k] = (graph[j][i] && graph[i][k]);
            }
        }
    }

    int s;
    cin >> s;
    for (int i = 0; i < s; i++) {
        cin >> u >> v;
        if (graph[u - 1][v - 1]) {
            cout <<  -1 << '\n';
        } else if (graph[v - 1][u - 1]) {
            cout << 1 << '\n';
        } else {
            cout << 0 << '\n';
        }
    }

    return 0;
}