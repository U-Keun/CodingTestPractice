#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

void topological_sort(int n, vector<vector<int>>& graph) {
    vector<int> in_degree(n, 0), answer;

    REP(i, 0, n - 1) {
        for (int nbd : graph[i]) {
            in_degree[nbd]++;
        }
    }

    priority_queue<int, vector<int>, greater<>> q;
    REP(i, 0, n - 1) {
        if (in_degree[i] == 0) q.push(i);
    }

    while (!q.empty()) {
        int cur = q.top();
        q.pop();
        answer.push_back(cur);
        for (int nbd : graph[cur]) {
            in_degree[nbd]--;
            if (in_degree[nbd] == 0) q.push(nbd);
        }
    }

    for (int node : answer) {
        cout << node + 1 << ' ';
    }
}

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<int>> graph(n);
    int u, v;
    while (m-- > 0) {
        cin >> u >> v;
        graph[u - 1].push_back(v - 1);
    }

    topological_sort(n, graph);

    return EXIT_SUCCESS;
}