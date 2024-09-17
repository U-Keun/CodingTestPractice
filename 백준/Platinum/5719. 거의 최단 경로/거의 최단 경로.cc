#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

struct edge {
public:
    int nextNode, length;
    edge(int nextNode, int length) : nextNode(nextNode), length(length) {}
};

int main() {
// int algorithm() {
    FAST_IO

    int n, m;
    cin >> n >> m;
    while (n != 0 && m != 0) {
        vector<vector<edge>> graph(n), reversedGraph(n);
        int s, e;
        cin >> s >> e;

        int u, v, p;
        REP(i, 1, m) {
            cin >> u >> v >> p;
            graph[u].emplace_back(edge(v, p));
            reversedGraph[v].emplace_back(edge(u, p));
        }

        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
        vector<int> recordLength(n, 500000);
        recordLength[s] = 0;
        unordered_map<int, unordered_map<int, bool>> recordEdges;
        pq.push({ 0, s });
        while (!pq.empty()) {
            int currentLength = pq.top().first,
                currentNode = pq.top().second;
            pq.pop();

            for (auto& nbd : graph[currentNode]) {
                if (currentLength > recordLength[nbd.nextNode]) continue;

                if (currentLength + nbd.length < recordLength[nbd.nextNode]) {
                    recordLength[nbd.nextNode] = currentLength + nbd.length;
                    pq.push({recordLength[nbd.nextNode], nbd.nextNode });
                }
            }
        }

        if (recordLength[e] == 500000) {
            cout << -1 << '\n';
            cin >> n >> m;
            continue;
        }

        queue<int> path;
        unordered_map<int, unordered_map<int, bool>> usedEdges;
        path.push(e);
        while (!path.empty()) {
            int current = path.front();
            path.pop();

            for (auto& nbd : reversedGraph[current]) {
                int prev = nbd.nextNode;
                int pathLength = nbd.length;

                if (recordLength[current] == recordLength[prev] + pathLength && !usedEdges[prev][current]) {
                    usedEdges[prev][current] = true;
                    path.push(prev);
                }
            }

        }

        REP(i, 0, n - 1) recordLength[i] = 500000;
        recordLength[s] = 0;
        pq.push({ 0, s });
        while (!pq.empty()) {
            int currentLength = pq.top().first,
                currentNode = pq.top().second;
            pq.pop();

            for (auto& nbd : graph[currentNode]) {
                if (usedEdges[currentNode][nbd.nextNode] || currentLength > recordLength[nbd.nextNode]) continue;

                if (currentLength + nbd.length < recordLength[nbd.nextNode]) {
                    recordLength[nbd.nextNode] = currentLength + nbd.length;
                    pq.push({recordLength[nbd.nextNode], nbd.nextNode });
                }
            }
        }

        if (recordLength[e] == 500000) cout << -1 << '\n';
        else cout << recordLength[e] << '\n';
        cin >> n >> m;
    }

    return 0;
}