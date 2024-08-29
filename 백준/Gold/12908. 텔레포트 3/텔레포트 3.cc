#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);
#define ll long long

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    vector<pair<int, int>> nodes(8);
    cin >> nodes[0].first >> nodes[0].second
        >> nodes[7].first >> nodes[7].second;

    vector<vector<ll>> adj(8, vector<ll>(8));
    int x1, y1, x2, y2;
    for (int i = 0; i < 3; i++) {
        cin >> x1 >> y1 >> x2 >> y2;

        adj[2 * i + 1][2 * i + 2] = 10;
        adj[2 * i + 2][2 * i + 1] = 10;

        nodes[2 * i + 1].first = x1;
        nodes[2 * i + 1].second = y1;
        nodes[2 * i + 2].first = x2;
        nodes[2 * i + 2].second = y2;
    }

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            if (i == j || adj[i][j]) continue;
            adj[i][j] = abs(nodes[i].first - nodes[j].first) + abs(nodes[i].second - nodes[j].second);
        }
    }

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                adj[j][k] = min(adj[j][k], adj[j][i] + adj[i][k]);
            }
        }
    }

    cout << adj[0][7];

    return 0;
}