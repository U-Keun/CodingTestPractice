#include <iostream>
#include <queue>
#include <vector>
using namespace std;

struct WeightedEdge {
    int nextVertex;
    long long weight;
    WeightedEdge(int v, long long w) : nextVertex(v), weight(w) {}
};

struct CompareEdge {
    bool operator()(const WeightedEdge &a, const WeightedEdge &b) {
        return a.weight > b.weight;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int N, K;
    cin >> N >> K;
    
    // 0부터 100000까지 정점을 다루며, 초기값은 100001 (문제에서 사용한 초기값과 동일)
    vector<long long> weightSum(100001, 100001);
    
    // 우선순위 큐를 이용한 다익스트라 알고리즘
    priority_queue<WeightedEdge, vector<WeightedEdge>, CompareEdge> queue;
    queue.push(WeightedEdge(N, 0));
    weightSum[N] = 0;
    
    while (!queue.empty()) {
        WeightedEdge tmp = queue.top();
        queue.pop();
        
        // 이미 더 짧은 경로를 찾은 경우 스킵
        if (weightSum[tmp.nextVertex] < tmp.weight)
            continue;
        
        // 2배 이동: 비용 0 (범위 체크)
        if (tmp.nextVertex <= 50000) {
            if (weightSum[2 * tmp.nextVertex] > tmp.weight) {
                weightSum[2 * tmp.nextVertex] = tmp.weight;
                queue.push(WeightedEdge(2 * tmp.nextVertex, tmp.weight));
            }
        }
        // 왼쪽 이동: 비용 1 (범위 체크)
        if (tmp.nextVertex > 0) {
            if (weightSum[tmp.nextVertex - 1] > tmp.weight + 1) {
                weightSum[tmp.nextVertex - 1] = tmp.weight + 1;
                queue.push(WeightedEdge(tmp.nextVertex - 1, tmp.weight + 1));
            }
        }
        // 오른쪽 이동: 비용 1 (범위 체크)
        if (tmp.nextVertex < 100000) {
            if (weightSum[tmp.nextVertex + 1] > tmp.weight + 1) {
                weightSum[tmp.nextVertex + 1] = tmp.weight + 1;
                queue.push(WeightedEdge(tmp.nextVertex + 1, tmp.weight + 1));
            }
        }
    }
    
    cout << weightSum[K] << "\n";
    return 0;
}