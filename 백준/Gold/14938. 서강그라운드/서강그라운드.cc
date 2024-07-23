#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

typedef struct weighted_edge {
    int next_vtx, weight;
    weighted_edge(int next_vtx, int wt) : next_vtx(next_vtx), weight(wt) {}

    bool operator<(const weighted_edge e) const {
        return this->weight > e.weight;
    }
} WeightedEdge;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n, m, r;
    cin >> n >> m >> r;

    vector<vector<WeightedEdge>> graph(n);

    int items[n];
    for (int i = 0; i < n; i++) {
        cin >> items[i];
    }

    int u, v, w;
    for (int i = 0; i < r; i++) { // 그래프를 저장하는 최선의 방법인가;
        cin >> u >> v >> w;

        graph[u - 1].push_back(WeightedEdge(v, w));
        graph[v - 1].push_back(WeightedEdge(u, w));
    }

    priority_queue<WeightedEdge> pque;
    int weight_sum[n];

    int answer = 0;
    for (int i = 0; i < n; i++) {
        pque.push(WeightedEdge(i + 1, 0));

        fill_n(weight_sum, n, 3001);
        weight_sum[i] = 0;

        while (!pque.empty()) {
            WeightedEdge tmp = pque.top(); pque.pop(); // poll()이 아니네?
            if (weight_sum[tmp.next_vtx - 1] < tmp.weight) continue;
            for (WeightedEdge edge : graph[tmp.next_vtx - 1]) {
                if (weight_sum[edge.next_vtx - 1] > tmp.weight + edge.weight) {
                    weight_sum[edge.next_vtx - 1] = tmp.weight + edge.weight;
                    pque.push(WeightedEdge(edge.next_vtx, weight_sum[edge.next_vtx - 1]));
                }
            }
        }

        int itemCount = 0;
        for (int j = 0; j < n; j++) {
            if (weight_sum[j] <= m) {
                itemCount += items[j];
            }
        }
        answer = max(answer, itemCount);
    }

    cout << answer << '\n';

    return 0;
}