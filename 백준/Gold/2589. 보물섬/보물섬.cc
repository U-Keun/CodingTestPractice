#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int row, col;
vector<vector<char>> map;

int dr[4] = { 0, -1, 1, 0 },
    dc[4] = { -1, 0, 0, 1 };

int calc_furthest(int i, int j) {
    vector<vector<bool>> visited(row, vector<bool>(col));
    queue<pair<int, int>> q;
    q.emplace(i, j);
    visited[i][j] = true;
    int length = -1;
    while (!q.empty()) {
        int l = q.size();
        while (l-- > 0) {
            int r = q.front().first,
                c = q.front().second;
            q.pop();
            REP(k, 0, 3) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr < 0 || nr >= row || nc < 0 || nc >= col ||
                    visited[nr][nc] || map[nr][nc] == 'W') continue;
                visited[nr][nc] = true;
                q.emplace(nr, nc);
            }
        }
        length++;
    }

    return length;
}

int main() {
    FAST_IO

    cin >> row >> col;
    map.resize(row, vector<char>(col));
    REP(i, 0, row - 1) REP(j, 0, col - 1) cin >> map[i][j];

    int answer = 0;
    REP(i, 0, row - 1) {
        REP(j, 0, col - 1) {
            if (map[i][j] == 'W') continue;
            answer = max(answer, calc_furthest(i, j));

        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}