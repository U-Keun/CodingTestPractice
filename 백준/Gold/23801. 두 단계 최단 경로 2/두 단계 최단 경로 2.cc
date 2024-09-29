#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define MAX 1000000000000
#define ll long long

vector<ll> dijkstra(int source, int n, vector<vector<pair<int, int>>>& graph) {
    vector<ll> dist(n, MAX);
    priority_queue<pair<ll, int>,
            vector<pair<ll, int>>,
                    greater<>> pq;
    pq.emplace(0, source);
    while (!pq.empty()) {
        ll wt = pq.top().first;
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
    while (m-- > 0) {
        cin >> u >> v >> w;
        graph[u - 1].push_back({ v - 1, w });
        graph[v - 1].push_back({ u - 1, w });
    }
    int x, z, p;
    cin >> x >> z >> p;
    vector<int> stopovers(p);
    for (int i = 0; i < p; i++) cin >> stopovers[i];

    vector<ll> fromX = dijkstra(x - 1, n, graph);
    vector<ll> fromZ = dijkstra(z - 1, n, graph);

    ll answer = -1;
    for (int stopover : stopovers) {
        if (fromX[stopover - 1] == MAX || fromZ[stopover - 1] == MAX) continue;
        if (answer == -1) {
            answer = fromX[stopover - 1] + fromZ[stopover - 1];
        } else {
            answer = min(answer, fromX[stopover - 1] + fromZ[stopover - 1]);
        }
    }

    cout << answer;

    return 0;
}