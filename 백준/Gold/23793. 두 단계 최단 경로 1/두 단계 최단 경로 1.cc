#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)
#define MAX 10000000000

vector<long long> dijkstra(int start, int n, const vector<vector<pair<int, int>>> &graph) {
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
            if (wt + edge.second < dist[edge.first]) {
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

    vector<long long> recordFromX = dijkstra(x - 1, n, graph);
    vector<long long> recordFromY = dijkstra(y - 1, n, graph);

    vector<long long> recordWithoutY(n, MAX);
    recordWithoutY[x - 1] = 0;

    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<>> pq;
    pq.emplace(0, x - 1);
    while (!pq.empty()) {
        long long wt = pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (wt > recordWithoutY[node]) continue;

        for (const auto& edge : graph[node]) {
            if (edge.first != y - 1 && wt + edge.second < recordWithoutY[edge.first]) {
                recordWithoutY[edge.first] = wt + edge.second;
                pq.emplace(recordWithoutY[edge.first], edge.first);
            }
        }
    }

    long long lengthWithY, lengthWithoutY;

    if (recordFromX[y - 1] >= MAX || recordFromY[z - 1] >= MAX)
        lengthWithY = MAX;
    else
        lengthWithY = recordFromX[y - 1] + recordFromY[z - 1];

    lengthWithoutY = recordWithoutY[z - 1];

    if (lengthWithY >= MAX) cout << -1;
    else cout << lengthWithY;
    cout << " ";
    if (lengthWithoutY >= MAX) cout << -1;
    else cout << lengthWithoutY;

    return 0;
}