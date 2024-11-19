#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    cin >> n;
    vector<vector<int>> map(n, vector<int>(n));
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) cin >> map[i][j];
    }

    queue<pair<int, int>> q;
    q.emplace(0, 0);
    while (!q.empty()) {
        int row = q.front().first, col = q.front().second;
        q.pop();
        if (map[row][col] <= 0) {
            if (map[row][col] == -1) map[row][col] = 0;
            continue;
        }

        if (row + map[row][col] < n) q.emplace(row + map[row][col], col);
        if (col + map[row][col] < n) q.emplace(row, col + map[row][col]);
    }

    if (map[n - 1][n - 1]) cout << "Hing";
    else cout << "HaruHaru";

    return 0;
}