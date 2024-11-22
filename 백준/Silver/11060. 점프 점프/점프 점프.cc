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

    vector<int> maze(n);
    REP(i, 0, n - 1) cin >> maze[i];

    queue<pair<int, int>> q;
    q.push({ 0, 0 });
    vector<bool> visited(n);
    visited[0] = true;
    int answer = 0;
    while (!q.empty()) {
        int tmp_idx = q.front().first, tmp_cnt = q.front().second;
        q.pop();
        if (tmp_idx == n -1) {
            cout << tmp_cnt;
            return EXIT_SUCCESS;
        }

        REP(i, 1, maze[tmp_idx]) {
            if (tmp_idx + i >= n || visited[tmp_idx + i]) continue;

            visited[tmp_idx + i] = true;
            q.push({ tmp_idx + i, tmp_cnt + 1 });
        }

    }

    cout << -1;

    return EXIT_SUCCESS;
}