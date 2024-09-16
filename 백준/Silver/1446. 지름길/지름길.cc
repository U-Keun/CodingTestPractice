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

    int n, d;
    cin >> n >> d;

    unordered_map<int, vector<pair<int, int>>> graph;

    int s, e, l;
    REP(i, 0, n - 1) {
        cin >> s >> e >> l;
        if (e > d || e - s <= l) continue;
        graph[s].push_back({ e, l });
    }
    vector<int> record(d + 1, 10001);
    record[0] = 0;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    pq.push({ 0, 0 });
    while (!pq.empty()) {
        pair<int, int> tmp = pq.top();
        pq.pop();

        if (tmp.first < record[tmp.second]) continue;

        if (tmp.second + 1 <= d && tmp.first + 1 < record[tmp.second + 1]) {
            record[tmp.second + 1] = tmp.first + 1;
            pq.push({ record[tmp.second + 1], tmp.second + 1 });
        }

        for (auto& nbd : graph[tmp.second]) {
            if (tmp.first + nbd.second < record[nbd.first]) {
                record[nbd.first] = tmp.first + nbd.second;
                pq.push({ record[nbd.first], nbd.first });
            }
        }
    }

    cout << record[d];

    return 0;
}