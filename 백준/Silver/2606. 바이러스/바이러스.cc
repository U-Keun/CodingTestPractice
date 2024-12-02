#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

vector<vector<int>> adj;
vector<bool> visited;

int dfs(int cur) {
    int answer = 0;

    if (visited[cur]) return answer;

    visited[cur] = true;
    answer++;
    for (int i = 0; i < adj[cur].size(); i++) {
        if (adj[cur][i] == 0) continue;
        answer += dfs(i);
    }

    return answer;
}

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    adj.resize(n, vector<int>(n, 0));
    visited.resize(n, false);

    int u, v;
    while (m-- > 0) {
        cin >> u >> v;
        adj[u - 1][v - 1] = 1;
        adj[v - 1][u - 1] = 1;
    }

    cout << dfs(0) - 1 << '\n';


    return EXIT_SUCCESS;
}