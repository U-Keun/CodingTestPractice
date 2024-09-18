#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <climits>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);
#define ll long long

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
// int algorithm() {
    FAST_IO

    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<pair<int, int>>> graph(n);
    int u, v, w;
    REP(i, 1, m) {
        cin >> u >> v >> w;
        graph[u - 1].push_back({ v - 1, w });
        graph[v - 1].push_back({ u - 1, w });
    }

    vector<vector<ll>> record(n, vector<ll>(k + 1, LLONG_MAX));
    record[0][0] = 0;
    priority_queue<tuple<ll, int, int>, vector<tuple<ll, int, int>>, greater<>> pq;
    pq.emplace(0, 0, 0);
    while (!pq.empty()) {
        ll wt = get<0>(pq.top());
        int node = get<1>(pq.top()),
            paved = get<2>(pq.top());
        pq.pop();
        if (wt > record[node][paved]) continue;
        for (auto& nbd : graph[node]) {
            int nextNode = nbd.first, edgeWeight = nbd.second;

            if (wt + edgeWeight < record[nextNode][paved]) {
                record[nextNode][paved] = wt + edgeWeight;
                pq.emplace(record[nextNode][paved], nextNode, paved);
            }

            if (paved < k && wt < record[nextNode][paved + 1]) {
                record[nextNode][paved + 1] = wt;
                pq.emplace(record[nextNode][paved + 1], nextNode, paved + 1);
            }
        }
    }

    ll answer = LLONG_MAX;
    REP(i, 0, k) {
        answer = min(answer, record[n - 1][i]);
    }

    cout << answer;

    return 0;
}