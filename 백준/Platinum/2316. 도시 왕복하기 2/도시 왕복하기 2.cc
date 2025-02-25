#include <iostream>
#include <vector>
#include <queue>
#include <climits>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n, p;
vector<vector<int>> capacity, flow;
vector<int> path_record;

bool find_path(int source, int sink) {
    path_record.assign(2 * n, -1);

    queue<int> q;
    q.push(source);
    path_record[source] = source;

    while (!q.empty()) {
        int tmp = q.front(); q.pop();

        REP(i, 0, 2 * n - 1) {
            if (path_record[i] == -1 && capacity[tmp][i] - flow[tmp][i] > 0) {
                path_record[i] = tmp;
                q.push(i);
                if (i == sink) return true;
            }
        }
    }

    return false;
}

int main() {
    FAST_IO

    cin >> n >> p;

    capacity.resize(2 * n, vector<int>(2 * n, 0));
    flow.resize(2 * n, vector<int>(2 * n, 0));
    REP(i, 0, n - 1) capacity[i][i + n] = 1;

    while (p--) {
        int u, v; cin >> u >> v;
        u--; v--;
        capacity[u + n][v] += 1;
        capacity[v + n][u] += 1;
    }

    int max_flow = 0, cur;
    while (find_path(n, 1)) {
        int path_flow = INT_MAX;
        cur = 1;
        while (cur != n) {
            int prev = path_record[cur];
            path_flow = min(path_flow, capacity[prev][cur] - flow[prev][cur]);
            cur = prev;
        }

        cur = 1;
        while (cur != n) {
            int prev = path_record[cur];
            flow[prev][cur] += path_flow;
            flow[cur][prev] -= path_flow;
            cur = prev;
        }

        max_flow += path_flow;
    }

    cout << max_flow;

    return EXIT_SUCCESS;
}