#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;
    vector<vector<bool>> adj(n, vector<bool>(n, false));
    int u, v;
    while (m-- > 0) {
        cin >> u >> v;
        adj[u - 1][v - 1] = true;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (adj[j][i] && adj[i][k]) adj[j][k] = true;
            }
        }
    }

    int answer = 0, count;
    for (int i = 0; i < n; i++) {
        count = 0;
        for (int j = 0; j < n; j++) {
            if (i != j && (adj[i][j] || adj[j][i])) count++;
        }
        if (count == n - 1) answer++;
    }

    cout << answer << '\n';

    return 0;
}