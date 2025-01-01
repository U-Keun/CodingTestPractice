#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int n, m;
vector<vector<int>> sea;

int dr[4] = { 0, -1, 1, 0 },
    dc[4] = { -1, 0, 0, 1 };

// 빙산이 녹는다 -> true : 빙산이 녹았다 / false : 녹을 빙산이 없다
bool iceberg_melt() {
    int melted = 0;
    vector<vector<int>> record(n, vector<int>(m));
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            if (sea[i][j]) continue;
            REP(k, 0, 3) {
                int r = i + dr[k], c = j + dc[k];
                if (r < 0 || r >= n || c < 0 || c >= m || !sea[r][c]) continue;
                record[r][c]--;
                melted++;
            }
        }
    }

    if (!melted) return false;

    REP(i, 1, n - 2) {
        REP(j, 1, m - 2) {
            if (!record[i][j]) continue;
            sea[i][j] += record[i][j];
            sea[i][j] = max(0, sea[i][j]);
        }
    }

    return true;
}

// 빙산이 쪼개졌는가
int get_components() {
    int components = 0;
    vector<vector<bool>> visited(n, vector<bool>(m));
    REP(i, 1, n - 2) {
        REP(j, 1, m - 2) {
            if (!sea[i][j] || visited[i][j]) continue;
            components++;
            queue<pair<int, int>> q;
            q.emplace(i, j);
            visited[i][j] = true;
            while (!q.empty()) {
                pair<int, int> cur = q.front(); q.pop();
                REP(k, 0, 3) {
                    int r = cur.first + dr[k], c = cur.second + dc[k];
                    if (r < 0 || r >= n || c < 0 || c >= m || !sea[r][c] || visited[r][c]) continue;
                    q.emplace(r, c);
                    visited[r][c] = true;
                }
            }
        }
    }

    return components;
}

int main() {
    FAST_IO

    cin >> n >> m;
    sea.resize(n, vector<int>(m));
    REP(i, 0, n - 1) REP(j, 0, m - 1) cin >> sea[i][j];

    int time = 0;
    bool has_never_separated = false;
    int res = get_components();
    while (res == 1) {
        if (iceberg_melt()) {
            time++;
            res = get_components();
            continue;
        } else {
            has_never_separated = true;
            break;
        }
    }

    if (has_never_separated || res == 0) cout << 0;
    else cout << time;

    return EXIT_SUCCESS;
}