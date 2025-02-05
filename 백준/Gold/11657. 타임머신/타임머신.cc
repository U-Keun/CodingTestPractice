#include <iostream>
#include <vector>
#include <limits>
using namespace std;

const long long INF = numeric_limits<long long>::max();

struct Edge {
    int source;
    int destination;
    long long weight;
    Edge(int s, int d, long long w) : source(s), destination(d), weight(w) {}
};

class Graph {
public:
    int vertexCount;
    vector<Edge> edges;

    Graph(int n) : vertexCount(n) { }

    void addEdge(int source, int destination, long long weight) {
        edges.emplace_back(source, destination, weight);
    }
};

class BellmanFord {
private:
    Graph &graph;
    vector<long long> distances; // 1-indexed: distances[1] ~ distances[vertexCount]
public:
    BellmanFord(Graph &g, int source) : graph(g) {
        distances.resize(graph.vertexCount + 1, INF);
        // 초기: source 정점의 거리는 0, 나머지는 INF
        distances[source] = 0;
        relaxWeights();
    }

    void relaxWeights() {
        int n = graph.vertexCount;
        // n-1번 반복해도 충분하지만, Java 코드에서는 n번 반복합니다.
        for (int i = 0; i < n; i++) {
            for (const Edge &edge : graph.edges) {
                if (distances[edge.source] == INF) continue;
                if (distances[edge.destination] > distances[edge.source] + edge.weight) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                }
            }
        }
    }

    const vector<long long>& getShortestDistances() const {
        return distances;
    }

    bool hasNegativeWeightedCycle() {
        // 한 번 더 relaxation 시도하여 변경되면 음수 사이클이 존재하는 것으로 판단
        for (const Edge &edge : graph.edges) {
            if (distances[edge.source] == INF) continue;
            if (distances[edge.destination] > distances[edge.source] + edge.weight) {
                return true;
            }
        }
        return false;
    }
};

bool isSourceIsolated(const vector<long long>& distances) {
    // 정점의 개수가 1이면 고립된 것으로 보지 않음.
    if (distances.size() <= 2) return false;
    // 2번 정점부터 마지막 정점까지 모두 INF면, source(1번 정점)는 다른 정점과 연결되어 있지 않은 것으로 판단.
    for (size_t i = 2; i < distances.size(); i++) {
        if (distances[i] != INF) return false;
    }
    return true;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;
    Graph graph(N);
    for (int i = 0; i < M; i++) {
        int a, b;
        long long t;
        cin >> a >> b >> t;
        // 주어진 Java 코드에서는 정점 번호가 그대로 사용됩니다.
        graph.addEdge(a, b, t);
    }

    // source는 1번 정점
    BellmanFord bellmanFord(graph, 1);
    const vector<long long>& distances = bellmanFord.getShortestDistances();

    // 결과를 저장할 문자열(또는 버퍼) 대신, 출력할 때 바로 처리합니다.
    // 2번 정점부터 N번 정점까지의 최단 거리를 출력합니다.
    // 만약 INF이면 -1을 출력합니다.
    bool sourceIsolated = isSourceIsolated(distances);
    if (sourceIsolated) {
        for (int i = 2; i <= N; i++) {
            cout << (distances[i] == INF ? -1 : distances[i]) << "\n";
        }
        return 0;
    }

    if (bellmanFord.hasNegativeWeightedCycle()) {
        cout << -1 << "\n";
        return 0;
    }

    for (int i = 2; i <= N; i++) {
        cout << (distances[i] == INF ? -1 : distances[i]) << "\n";
    }
    return 0;
}