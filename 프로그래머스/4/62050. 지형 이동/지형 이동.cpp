#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#include <iostream>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int dx[4] = { -1, 0, 0, 1 },
	dy[4] = { 0, 1, -1, 0 };
vector<int> parents;

int find(int node) {
    if (parents[node] != node) {
        parents[node] = find(parents[node]);  // 경로 압축
    }
    return parents[node];
}

bool unionTree(int u, int v) {
    u = find(u);
    v = find(v);
    if (u == v) return false;
    if (u < v) parents[v] = u;
    else parents[u] = v;
    return true;
}

int solution(vector<vector<int>> land, int height) {
    int n = land.size();
    vector<vector<int>> visited(n, vector<int>(n, -1));
    // 장소 구분만
    int region = 0;
    queue<pair<int, int>> posQueue;
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            if (visited[i][j] >= 0) continue;
            visited[i][j] = region;
            posQueue.push(make_pair(i, j));
            while (!posQueue.empty()) {
                pair<int, int> tmp = posQueue.front();
                posQueue.pop();
                REP(k, 0, 3) {
                    int row = tmp.first + dy[k], col = tmp.second + dx[k];
                    if (row >= 0 && row < n
                       && col >= 0 && col < n
                       && visited[row][col] < 0
                       && abs(land[tmp.first][tmp.second] - land[row][col]) <= height) {
                        visited[row][col] = region;
                        posQueue.push(make_pair(row, col));
                    }
                }
            }
            region++;
    	}
    }
    
    // 그래프 -> MST..? Kruskal's algorithm 써보자.
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> edges;
    
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            if (j < n - 1 && visited[i][j] != visited[i][j + 1]) {
                int val = abs(land[i][j] - land[i][j + 1]);
                edges.push({val, visited[i][j], visited[i][j + 1]});
            }
            if (i < n - 1 && visited[i][j] != visited[i + 1][j]) {
                int val = abs(land[i][j] - land[i + 1][j]);
                edges.push({val, visited[i][j], visited[i + 1][j]});
            }
        }
    }
    
    parents.resize(region);
    REP(i, 0, region - 1) parents[i] = i;
    
    int answer = 0, count = 0;
    while (count < region - 1) {
        auto [weight, u, v] = edges.top();
        edges.pop();
        if (unionTree(u, v)) {
            count++;
            answer += weight;
        }
    }
    
    return answer;
}