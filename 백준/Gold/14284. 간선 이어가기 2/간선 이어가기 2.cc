#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    unordered_map<int, vector<pair<int, int>>> graph;

    int a, b, c;
    REP(i, 0, m - 1) {
        cin >> a >> b >> c;
        graph[a - 1].emplace_back(b - 1, c);
        graph[b - 1].emplace_back(a - 1, c);
    }

    int s, t;
    cin >> s >> t;

    vector<int> record(n, 500001);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    record[s - 1] = 0;
    pq.push({ 0, s - 1 });

    while (!pq.empty()) {
        pair<int, int> tmp = pq.top();
        pq.pop();

        for (pair<int, int> node : graph[tmp.second]) {
            if (tmp.first + node.second < record[node.first]) {
                record[node.first] = tmp.first + node.second;
                pq.push({ record[node.first], node.first });
            }
        }
    }

    cout << record[t - 1];

    return 0;
}