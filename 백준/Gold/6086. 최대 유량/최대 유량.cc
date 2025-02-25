#include <iostream>
#include <vector>
#include <queue>
#include <climits>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<vector<int>> capacity, flow;
vector<int> path_record;

bool find_path(int source, int sink) {
    path_record.assign(52, -1);

    queue<int> q;
    q.push(source);
    path_record[source] = source;

    while (!q.empty()) {
        int tmp = q.front(); q.pop();

        REP(i, 0, 51) {
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

    capacity.resize(52, vector<int>(52, 0));
    flow.resize(52, vector<int>(52, 0));
    int n; cin >> n;
    while (n--) {
        char x, y; cin >> x >> y;
        int i = isupper(x) ? x - 'A' : x - 'a' + 26,
            j = isupper(y) ? y - 'A' : y - 'a' + 26;

        int v; cin >> v;
        capacity[i][j] += v;
        capacity[j][i] += v;
    }

    int max_flow = 0, cur;
    while (find_path(0, 25)) {
        int path_flow = INT_MAX;
        cur = 25;
        while (cur != 0) {
            int prev = path_record[cur];
            path_flow = min(path_flow, capacity[prev][cur] - flow[prev][cur]);
            cur = prev;
        }

        cur = 25;
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