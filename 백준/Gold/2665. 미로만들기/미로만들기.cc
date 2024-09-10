#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int dx[4] = { -1, 0, 0, 1 },
    dy[4] = { 0, -1, 1, 0 };

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    vector<vector<char>> maze(n, vector<char>(n));
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            cin >> maze[i][j];
        }
    }

    vector<vector<int>> visited(n, vector<int>(n, 100));
    queue<pair<int, int>> q;
    visited[0][0] = 0;
    q.push({ 0, 0 });
    while (!q.empty()) {
        pair<int, int> pos = q.front();
        q.pop();
        REP(i, 0, 3) {
            int row = pos.first + dy[i], col = pos.second + dx[i];
            if (row < 0 || col < 0 || row >= n || col >= n) continue;
            if (maze[row][col] == '1') {
                if (visited[row][col] > visited[pos.first][pos.second]) {
                    visited[row][col] = visited[pos.first][pos.second];
                    q.push({ row, col });
                }
            } else {
                if (visited[row][col] > visited[pos.first][pos.second] + 1) {
                    visited[row][col] = visited[pos.first][pos.second] + 1;
                    q.push({ row, col });
                }
            }
        }
    }

    cout << visited[n - 1][n - 1];

    return 0;
}