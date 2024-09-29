#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)
#define MAX 10000000000

vector<long long> dijkstra(int start, int n, const vector<vector<pair<int, int>>> &graph, int y) {
    vector<long long> dist(n, MAX);
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<>> pq;
    pq.emplace(0, start);
    dist[start] = 0;

    while (!pq.empty()) {
        long long wt = pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (wt > dist[node]) continue;

        for (const auto& edge : graph[node]) {
            if ((y < 0 || edge.first != y) && wt + edge.second < dist[edge.first]) {
                dist[edge.first] = wt + edge.second;
                pq.emplace(dist[edge.first], edge.first);
            }
        }
    }

    return dist;
}

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, int>>> graph(n);
    int u, v, w;
    REP(i, 0, m - 1) {
        cin >> u >> v >> w;
        graph[u - 1].push_back({ v - 1, w });
    }

    int x, y, z;
    cin >> x >> y >> z;

    vector<long long> recordFromX = dijkstra(x - 1, n, graph, -1);
    vector<long long> recordFromY = dijkstra(y - 1, n, graph, -1);
    vector<long long> recordWithoutY = dijkstra(x - 1, n, graph, y - 1);


    if (recordFromX[y - 1] >= MAX || recordFromY[z - 1] >= MAX) cout << -1;
    else cout << recordFromX[y - 1] + recordFromY[z - 1];
    cout << " ";
    if (recordWithoutY[z - 1] >= MAX) cout << -1;
    else cout << recordWithoutY[z - 1];

    return 0;
}