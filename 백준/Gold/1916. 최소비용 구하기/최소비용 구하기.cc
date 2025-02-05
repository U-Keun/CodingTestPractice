#include <iostream>
#include <vector>
#include <queue>
#include <limits>

using namespace std;

struct WeightedEdge {
    int vertex;        // 0-indexed 정점 번호
    long long weight;  // 경로의 가중치
    WeightedEdge(int v, long long w) : vertex(v), weight(w) {}
};

struct CompareEdge {
    bool operator()(const WeightedEdge &a, const WeightedEdge &b) {
        return a.weight > b.weight;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N;
    cin >> M;

    // 그래프 및 최단거리 배열 초기화
    vector<vector<WeightedEdge>> graph(N);
    vector<long long> weightSum(N, numeric_limits<long long>::max());

    // 간선 정보 입력 (입력된 정점 번호는 1-indexed이므로 0-indexed로 변환)
    for (int i = 0; i < M; i++) {
        int v1, v2, wt;
        cin >> v1 >> v2 >> wt;
        graph[v1 - 1].push_back(WeightedEdge(v2 - 1, wt));
    }

    int initial, terminal;
    cin >> initial >> terminal;
    // 시작점과 도착점을 0-indexed로 변환
    initial--;  
    terminal--;

    // 다익스트라 알고리즘
    priority_queue<WeightedEdge, vector<WeightedEdge>, CompareEdge> pq;
    weightSum[initial] = 0;
    pq.push(WeightedEdge(initial, 0));

    while (!pq.empty()) {
        WeightedEdge cur = pq.top();
        pq.pop();

        // 현재 경로보다 이미 더 짧은 경로가 존재하면 건너뜁니다.
        if (weightSum[cur.vertex] < cur.weight)
            continue;

        // 인접 정점들에 대해 최단 경로 갱신 시도
        for (const auto &edge : graph[cur.vertex]) {
            if (weightSum[edge.vertex] > cur.weight + edge.weight) {
                weightSum[edge.vertex] = cur.weight + edge.weight;
                pq.push(WeightedEdge(edge.vertex, weightSum[edge.vertex]));
            }
        }
    }

    cout << weightSum[terminal] << "\n";
    return 0;
}