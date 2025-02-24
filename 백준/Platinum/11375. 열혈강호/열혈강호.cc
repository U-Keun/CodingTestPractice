#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n, m;
vector<vector<int>> graph;
vector<int> record;
vector<bool> visited;

bool find_matching(int worker) {
    for (int task : graph[worker]) {
        if (visited[task]) continue;
        visited[task] = true;
        if (record[task] == 0 || find_matching(record[task])) {
            record[task] = worker;
            return true;
        }
    }
    return false;
}

int main() {
    FAST_IO

    cin >> n >> m;
    graph.resize(n + 1);

    REP(i, 1, n) {
        int e; cin >> e;
        while (e--) {
            int node; cin >> node;
            graph[i].push_back(node);
        }
    }

    record.resize(m + 1, 0);;
    int answer = 0;
    REP(worker, 1, n) {
        visited.assign(m + 1, false);
        if (find_matching(worker)) answer++;
    }

    cout << answer;

    return EXIT_SUCCESS;
}