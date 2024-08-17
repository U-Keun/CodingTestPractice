#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;
    vector<vector<bool>> graph(n, vector<bool>(n, false));
    int m;
    cin >> m;
    int u, v;
    for (int i = 0; i < m; i++) {
        cin >> u >> v;
        graph[u - 1][v - 1] = true;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (graph[j][i] && graph[i][k]) graph[j][k] = true;
            }
        }
    }

    int count;
    for (int i = 0; i < n; i++) {
        count = 0;
        for (int j = 0; j < n; j++) {
            if (i != j && !graph[i][j] && !graph[j][i]) count++;
        }
        cout << count <<  '\n';
    }

    return 0;
}