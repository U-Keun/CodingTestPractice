#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int n, k;
vector<vector<int>> graph, visited;

int findMinTime(int current, int visitedMask) {
    if (visitedMask == (1 << n) - 1) {
        return 0;
    }

    int minTime = 100000001;
    for (int next = 0; next < n; next++) {
        if (!(visitedMask & (1 << next))) {
            int timeToNext = graph[current][next] + findMinTime(next, visitedMask | (1 << next));
            minTime = min(minTime, timeToNext);
        }
    }

    return minTime;
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> n >> k;

    graph.resize(n, vector<int>(n));
    visited.resize(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
            if (graph[i][j] != 0) visited[i][j] = (1 << i) | (1 << j);
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (graph[j][k] > graph[j][i] + graph[i][k]) {
                    graph[j][k] = graph[j][i] + graph[i][k];
                    visited[j][k] = visited[j][i] | visited[i][k];
                }
            }
        }
    }

    cout << findMinTime(k, 1 << k);

    return 0;
}