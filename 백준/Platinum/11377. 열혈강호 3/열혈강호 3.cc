#include <iostream>
#include <vector>
#include <queue>
#include <climits>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n, m, k;
vector<vector<int>> capacity, flow;
vector<int> path_record;

bool find_path(int source, int sink) {
    path_record.assign(n + m + 3, -1);

    queue<int> q;
    q.push(source);
    path_record[source] = source;

    while (!q.empty()) {
        int tmp = q.front(); q.pop();

        REP(i, 0, n + m + 2) {
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

    cin >> n >> m >> k;
    capacity.resize(n + m + 3, vector<int>(n + m + 3));
    REP(i, 1, n) {
        capacity[0][i] = 1;
        int l; cin >> l;
        while (l--) {
            int w; cin >> w;
            capacity[i][n + w] += 1;
        }
    }

    capacity[0][n + m + 2] = k;
    REP(i, 1, n) capacity[n + m + 2][i] = 1;

    REP(i, 1, m) capacity[n + i][n + m + 1] = 1;
    flow.resize(n + m + 3, vector<int>(n + m + 3));

    int max_flow = 0;
    while (find_path(0, n + m + 1)) {
        int path_flow = INT_MAX, cur = n + m + 1;
        while (cur != 0) {
            int prev = path_record[cur];
            path_flow = min(path_flow, capacity[prev][cur] - flow[prev][cur]);
            cur = prev;
        }

        cur = n + m + 1;
        while (cur != 0) {
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