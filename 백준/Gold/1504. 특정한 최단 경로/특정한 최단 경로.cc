#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO() \
    ios::sync_with_stdio(false); \
    cin.tie(NULL); \
    cout.tie(NULL);
#define MAX_WEIGHT 200000000
#define ll long long

using namespace std;

int n;

struct node {
    int index;
    ll dist;
    node() {}
    node(int index, ll dist) : index(index), dist(dist) {}
    int operator < (const node other) const {
        return this->dist > other.dist;
    }
};

vector<ll> dijkstra(vector<vector<int> >& graph, int source) {
    vector<ll> weight(n, MAX_WEIGHT);
    priority_queue<node> queue;
    queue.push(node(source, 0));
    weight[source] = 0;

    while (!queue.empty()) {
        node tmp = queue.top();
        queue.pop();
        if (weight[tmp.index] < tmp.dist) continue;
        for (int i = 0; i < n; i++) {
            if (graph[tmp.index][i] == 0) continue;
            ll value = weight[tmp.index] + graph[tmp.index][i];
            if (weight[i] > value) {
                weight[i] = value;
                queue.push(node(i, value));
            }
        }
    }
    return weight;
}

int main() {
// int algorithm() {
    FAST_IO();

    int e;
    cin >> n >> e;

    vector<vector<int> > graph(n, vector<int>(n, 0));
    int u, v, w;
    while (e-- > 0) {
        cin >> u >> v >> w;
        graph[u - 1][v - 1] = w;
        graph[v - 1][u - 1] = w;
    }


    int d1, d2;
    cin >> d1 >> d2;

    vector<ll> fromStart = dijkstra(graph, 0),
        toEnd = dijkstra(graph, n - 1),
        fromD1 = dijkstra(graph, d1 - 1);

     ll path1 = fromStart[d1 - 1] + fromD1[d2 - 1] + toEnd[d2 - 1],
        path2 = fromStart[d2 - 1] + fromD1[d2 - 1] + toEnd[d1 - 1];

    if (min(path1, path2) >= MAX_WEIGHT) cout << -1 << '\n';
    else cout << min(path1, path2) << '\n';

    return 0;
}