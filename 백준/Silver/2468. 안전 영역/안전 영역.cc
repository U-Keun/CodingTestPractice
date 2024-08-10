#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int n, unfloodedSites, visitCount = 1;
vector< vector<int> > map, visited;
int dx[4] = { -1, 0, 0, 1 }, dy[4] = { 0, 1, -1, 0 };

bool isMovable(const int row, const int col) {
    return row >= 0 && row < n && col >= 0 && col < n;
}

int countSafetyZones(const int height) {
    int safetyZones = 0;
    queue< pair<int, int> > q;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (map[i][j] <= height || visited[i][j] == visitCount) continue;

            safetyZones++;
            q.push(make_pair(i, j));
            visited[i][j] = visitCount;

            while (!q.empty()) {
                auto [row, col] = q.front();
                q.pop();

                for (int m = 0; m < 4; m++) {
                    int newRow = row + dx[m], newCol = col + dy[m];
                    if (isMovable(newRow, newCol) && visited[newRow][newCol] != visitCount && map[newRow][newCol] > height) {
                        q.push(make_pair(newRow, newCol));
                        visited[newRow][newCol] = visitCount;
                    }
                }
            }
        }
    }

    visitCount++;
    return safetyZones;
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> n;
    map.resize(n, vector<int>(n));
    visited.resize(n, vector<int>(n, 0));

    int maxHeight = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> map[i][j];
            maxHeight = max(maxHeight, map[i][j]);
        }
    }

    int answer = 1;
    for (int height = 1; height < maxHeight; height++) {
        answer = max(answer, countSafetyZones(height));
    }

    cout << answer << '\n';

    return 0;
}