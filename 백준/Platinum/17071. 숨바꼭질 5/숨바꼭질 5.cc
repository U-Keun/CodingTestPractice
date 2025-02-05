#include <iostream>
#include <queue>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define MIN_POS 0
#define MAX_POS 500000

int moved_dist(int x) {
    return x * (x + 1) / 2;
}

int main() {
    FAST_IO

    int n, k; cin >> n >> k;
    queue<pair<int, int>> q;
    vector<vector<bool>> visited(MAX_POS + 1, vector<bool>(2, false));
    q.push({ n, 0 });
    visited[n][0] = true;
    while (!q.empty()) {
        auto &tmp = q.front();
        int cur = tmp.first, time_past = tmp.second;
        q.pop();
        int target = k + moved_dist(time_past);
        if (target > MAX_POS) continue;

        if (visited[target][time_past % 2]) {
            cout << time_past;
            return EXIT_SUCCESS;
        }

        time_past++;
        if (cur - 1 >= MIN_POS && !visited[cur - 1][time_past % 2]) {
            visited[cur - 1][time_past % 2] = true;
            q.push({ cur - 1, time_past });
        }
        if (cur + 1 <= MAX_POS && !visited[cur + 1][time_past % 2]) {
            visited[cur + 1][time_past % 2] = true;
            q.push({ cur + 1, time_past });
        }
        if (cur * 2 <= MAX_POS && !visited[cur * 2][time_past % 2]) {
            visited[cur * 2][time_past % 2] = true;
            q.push({ cur * 2, time_past });
        }
    }

    cout << -1;
    return EXIT_SUCCESS;
}