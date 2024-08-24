#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int T;
    cin >> T;
    while (T-- > 0) {
        int N, M;
        cin >> N >> M;
        vector<vector<int>> adj(N, vector<int>(N, 100001));
        for (int i = 0; i < N; i++) adj[i][i] = 0;
        int u, v, w;
        for (int i = 0; i < M; i++) {
            cin >> u >> v >> w;
            adj[u - 1][v - 1] = w;
            adj[v - 1][u - 1] = w;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    adj[j][k] = min(adj[j][k], adj[j][i] + adj[i][k]);
                }
            }
        }

        int K;
        cin >> K;
        vector<int> party(K);
        for (int i = 0; i < K; i++) cin >> party[i];
        int minimum = 100001, answer = -1;
        for (int i = N - 1; i >= 0; i--) {
            int val = 0;
            for (int member : party) {
                val += adj[member - 1][i];
            }
            if (minimum >= val) {
                minimum = val;
                answer = i + 1;
            }
        }

        cout << answer << '\n';
    }

    return 0;
}