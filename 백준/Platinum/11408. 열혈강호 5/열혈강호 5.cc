#include <iostream>
#include <vector>
#include <queue>
#include <climits>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n, m;
vector<vector<int>> capacity, flow, cost;
vector<int> path_record, dist;

bool find_path(int source, int sink) {
    path_record.assign(n + m + 2, -1);
    dist.assign(n + m + 2, INT_MAX);
    dist[source] = 0;

    queue<int> q;
    q.push(source);
    path_record[source] = source;

    vector<bool> in_queue(n + m + 2, false);
    in_queue[source] = true;

    while (!q.empty()) {
        int tmp = q.front(); q.pop();
        in_queue[tmp] = false;

        REP(i, 0, n + m + 1) {
            if (capacity[tmp][i] - flow[tmp][i] > 0 && dist[tmp] != INT_MAX) {
                int d = dist[tmp] + cost[tmp][i];
                if (d < dist[i]) {
                    dist[i] = d;
                    path_record[i] = tmp;
                    if (!in_queue[i]) {
                        in_queue[i] = true;
                        q.push(i);
                    }
                }

            }
        }
    }

    return dist[sink] != INT_MAX;
}

int main() {
    FAST_IO

    cin >> n >> m;
    capacity.resize(n + m + 2, vector<int>(n + m + 2));
    cost.resize(n + m + 2, vector<int>(n + m + 2));
    REP(i, 1, n) {
        capacity[0][i] = 1;
        int k; cin >> k;
        while (k--) {
            int w, c; cin >> w >> c;
            capacity[i][n + w] += 1;
            cost[i][n + w] += c;
            cost[n + w][i] -= c;
        }
    }

    REP(i, 1, m) capacity[n + i][n + m + 1] = 1;
    flow.resize(n + m + 2, vector<int>(n + m + 2));

    int min_cost = 0, max_flow = 0;
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
            min_cost += cost[prev][cur] * path_flow;
            cur = prev;
        }

        max_flow += path_flow;
    }

    cout << max_flow << '\n' << min_cost;

    return EXIT_SUCCESS;
}