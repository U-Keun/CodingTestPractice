#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int INF = 3000001;

struct WeightedEdge {
    int vertex;  // 0-indexed vertex 번호
    int weight;
    WeightedEdge(int v, int w) : vertex(v), weight(w) {}
};

// 우선순위 큐에서 작은 weight가 높은 우선순위를 갖도록 비교 연산자 정의
struct CompareEdge {
    bool operator()(const WeightedEdge &a, const WeightedEdge &b) {
        return a.weight > b.weight;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;
    int K;
    cin >> K;

    // 그래프: 각 정점에 대한 인접 리스트 (0-indexed)
    vector<vector<WeightedEdge>> graph(N);
    // 시작점 K의 최단거리 배열 (초기값은 INF)
    vector<int> weightSum(N, INF);

    // 간선 입력 처리 (정점 번호는 입력에서 1-indexed이므로 0-indexed로 변환)
    for (int i = 0; i < M; i++) {
        int v1, v2, wt;
        cin >> v1 >> v2 >> wt;
        graph[v1 - 1].push_back(WeightedEdge(v2 - 1, wt));
    }

    // 다익스트라 알고리즘 수행 (시작 정점은 K-1)
    priority_queue<WeightedEdge, vector<WeightedEdge>, CompareEdge> pq;
    weightSum[K - 1] = 0;
    pq.push(WeightedEdge(K - 1, 0));

    while (!pq.empty()) {
        WeightedEdge cur = pq.top();
        pq.pop();

        // 이미 더 짧은 경로가 존재하면 현재 노드는 건너뜀
        if (weightSum[cur.vertex] < cur.weight)
            continue;

        // 인접 정점들을 확인하며 경로 갱신
        for (const auto &edge : graph[cur.vertex]) {
            if (weightSum[edge.vertex] > cur.weight + edge.weight) {
                weightSum[edge.vertex] = cur.weight + edge.weight;
                pq.push(WeightedEdge(edge.vertex, weightSum[edge.vertex]));
            }
        }
    }

    // 결과 출력: INF보다 큰 값은 도달 불가능한 것으로 간주
    for (int i = 0; i < N; i++) {
        if (weightSum[i] >= INF)
            cout << "INF\n";
        else
            cout << weightSum[i] << "\n";
    }

    return 0;
}