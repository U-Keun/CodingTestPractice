#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> edges;
int dp[100001];
bool visited[100001];

void dfs(int node);

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    ios_base::sync_with_stdio(false);
    
    int N, R, Q;
    cin >> N >> R >> Q;
    
    edges.resize(N + 1);
    
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        edges[u - 1].push_back(v);
        edges[v - 1].push_back(u);
    }
    
    for (int i = 1; i <= N; i++) {
        dp[i] = 1;
    }
    
    dfs(R);
    
    for (int i = 0; i < Q; i++) {
        int q;
        cin >> q;
        cout << dp[q] << '\n';
    }
    
}

void dfs(int node) {
    visited[node] = true;
    
    for (int neighbor : edges[node - 1]) {
        if (visited[neighbor]) continue;
        dfs(neighbor);
        dp[node] = dp[node] + dp[neighbor];
    }
}