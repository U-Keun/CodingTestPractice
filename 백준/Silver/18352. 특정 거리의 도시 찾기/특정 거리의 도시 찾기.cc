#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP_INC(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n, m, k, x;
    cin >> n >> m >> k >> x;

    vector<vector<int>> graph(n);
    vector<int> record(n, 300000);
    record[x - 1] = 0;
    int u, v;
    REP_INC(i, 0, m - 1) {
        cin >> u >> v;
        graph[u - 1].push_back(v - 1);
    }

    auto cmp = [&](int a, int b) {
        return record[a] > record[b];
    };

    priority_queue<int, vector<int>, decltype(cmp)> nodes(cmp);
    vector<bool> visited(n, false);
    nodes.push(x - 1);
    while (!nodes.empty()) {
        int tmp = nodes.top();
        nodes.pop();
        visited[tmp] = true;
        for (int idx : graph[tmp]) {
            if (visited[idx]) continue;
            if (record[idx] > record[tmp] + 1) {
                record[idx] = record[tmp] + 1;
                nodes.push(idx);
            }
        }
    }

    int count = 0;
    REP_INC(i, 0, n - 1) {
        if (record[i] == k) {
            cout << (i + 1) << '\n';
            count++;
        }
    }

    if (count == 0) cout << -1;

    return 0;
}