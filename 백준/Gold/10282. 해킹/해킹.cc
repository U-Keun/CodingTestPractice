#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int t;
    cin >> t;

    int n, d, c, a, b, s;
    REP(i, 0, t - 1) {
        cin >> n >> d >> c;
        unordered_map<int, vector<pair<int, int>>> graph;

        REP(j, 0, d - 1) {
            cin >> a >> b >> s;
            graph[b - 1].push_back({ s, a - 1 });
        }

        vector<int> record(n, 100000001);
        record[c - 1] = 0;
        vector<bool> visited(n, false);
        visited[c - 1] = true;

        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        pq.push({ 0, c - 1 });
        while (!pq.empty()) {
            pair<int, int> tmp = pq.top();
            visited[tmp.second] = true;
            pq.pop();

            if (tmp.first > record[tmp.second]) continue;

            for (pair<int, int> node : graph[tmp.second]) {
                if (visited[node.second]) continue;

                if (record[tmp.second] + node.first < record[node.second]) {
                    record[node.second] = record[tmp.second] + node.first;
                    pq.push({ record[node.second], node.second });
                }
            }
        }

        int count = 0, answer = 0;
        REP(j, 0, n - 1) {
            if (record[j] == 100000001) continue;
            count++;
            answer = max(answer, record[j]);
        }

        cout << count << ' ' << answer << '\n';
    }

    return 0;
}