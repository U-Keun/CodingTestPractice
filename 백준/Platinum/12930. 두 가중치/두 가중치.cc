#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;
    vector<vector<int>> graph1(n, vector<int>(n)),
                        graph2(n, vector<int>(n));
    char wt;
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            cin >> wt;
            if (wt == '.') continue;
            graph1[i][j] = (wt - '0');
        }
    }
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            cin >> wt;
            if (wt == '.') continue;
            graph2[i][j] = (wt - '0');
        }
    }

    vector<vector<int>> record(n, vector<int>(200, 200));
    record[0][0] = 0;
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<>> pq;
    pq.emplace(0, 0, 0);
    while (!pq.empty()) {
        int wt2 = get<0>(pq.top()),
            node = get<1>(pq.top()),
            wt1 = get<2>(pq.top());
        pq.pop();

        REP(i, 0, n - 1) {
            if (!graph2[node][i] || wt1 + graph1[node][i] >= 200) continue;

            if (wt2 + graph2[node][i] < record[i][wt1 + graph1[node][i]]) {
                record[i][wt1 + graph1[node][i]] = wt2 + graph2[node][i];
                pq.emplace(record[i][wt1 + graph1[node][i]], i, wt1 + graph1[node][i]);
            }
        }
    }

    int answer = -1;
    REP(i, 1, 199) {
        if (record[1][i] == 200) continue;
        else if (answer == -1 || answer > i * record[1][i]) answer = i * record[1][i];
    }

    cout << answer;

    return 0;
}