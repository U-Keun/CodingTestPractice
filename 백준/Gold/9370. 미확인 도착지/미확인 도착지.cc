#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

vector<int> dijkstra(unordered_map<int, vector<pair<int, int>>> graph,
                     int nodes,
                     int source) {
    vector<int> record(nodes, 1000000000);
    record[source - 1] = 0;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({ 0, source - 1 });

    while (!pq.empty()) {
        pair<int, int> tmp = pq.top();
        pq.pop();

        if (tmp.first > record[tmp.second]) continue;

        for (pair<int, int> nbd : graph[tmp.second]) {
            int val = record[tmp.second] + nbd.first;
            if (val < record[nbd.second]) {
                record[nbd.second] = val;
                pq.push({ record[nbd.second], nbd.second });
            }
        }
    }

    return record;
}

int main() {
// int algorithm() {
    FAST_IO

    int T;
    cin >> T;

    int n, m, t, s, g, h, a, b, d, smell;
    while (T-- > 0) {
        cin >> n >> m >> t;
        unordered_map<int, vector<pair<int, int>>> graph;
        cin >> s >> g >> h;
        while (m-- > 0) {
            cin >> a >> b >> d;
            graph[a - 1].push_back({ d, b - 1 });
            graph[b - 1].push_back({ d, a - 1 });
            if ((a == g && b == h) || (a == h && b == g)) smell = d;
        }

        vector<int> first = dijkstra(graph, n, s),
                    fromG = dijkstra(graph, n, g),
                    fromH = dijkstra(graph, n, h);

        priority_queue<int, vector<int>, greater<int>> pq;
        int x;
        while (t-- > 0) {
            cin >> x;
            if (first[x - 1] == 1000000000) continue;
            if (first[x - 1] == first[g - 1] + smell + fromH[x - 1]
                || first[x - 1] == first[h - 1] + smell + fromG[x - 1]) {
                pq.push(x);
            }
        }

        while (!pq.empty()) {
            cout << pq.top() << ' ';
            pq.pop();
        }

        cout << '\n';
    }

    return 0;
}