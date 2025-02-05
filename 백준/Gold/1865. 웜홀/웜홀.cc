#include <iostream>
#include <vector>
#include <string>
using namespace std;

struct Edge {
    int source;
    int destination;
    long long weight;
    Edge(int s, int d, long long w) : source(s), destination(d), weight(w) { }
};

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int TC;
    cin >> TC;
    string output;
    while (TC--) {
        int N, M, W;
        cin >> N >> M >> W;
        vector<Edge> edges;
        // 일반 간선: 양방향 간선으로 처리 (단, s와 e가 다를 때만 역방향 추가)
        for (int i = 0; i < M; i++) {
            int s, e;
            long long t;
            cin >> s >> e >> t;
            edges.push_back(Edge(s, e, t));
            if (s != e) {
                edges.push_back(Edge(e, s, t));
            }
        }
        // 웜홀: 단방향으로, 가중치는 음수 처리 (-t)
        for (int i = 0; i < W; i++) {
            int s, e;
            long long t;
            cin >> s >> e >> t;
            edges.push_back(Edge(s, e, -t));
        }
        // distances 배열은 음의 사이클 여부 판단을 위해 모두 0으로 초기화 (1번부터 N번까지 사용)
        vector<long long> distances(N + 1, 0LL);
        bool timeTravel = false;
        // Bellman-Ford 알고리즘: N번의 반복 후 한 번 더 반복하여 음의 사이클 존재 여부를 확인
        for (int j = 0; j <= N; j++) {
            bool updated = false;
            for (const auto &edge : edges) {
                if (distances[edge.destination] > distances[edge.source] + edge.weight) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                    updated = true;
                    if (j == N) {  // N번째 반복에서도 갱신되면 음의 사이클 존재
                        timeTravel = true;
                        break;
                    }
                }
            }
            if (timeTravel) break;
            // 더 이상 갱신이 없으면 음의 사이클은 없으므로 반복 종료
            if (!updated) break;
        }
        output += (timeTravel ? "YES\n" : "NO\n");
    }
    cout << output;
    return 0;
}