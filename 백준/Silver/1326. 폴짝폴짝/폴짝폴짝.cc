#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, from, to;
    cin >> n;
    vector<int> bridge(n);
    REP(i, 0,  n - 1) cin >> bridge[i];
    cin >> from >> to;

    vector<bool> visited(n);
    queue<int> q;
    q.push(from - 1);
    int answer = 0;
    while (!q.empty()) {
        int l = q.size();
        while (l-- > 0) {
            int cur = q.front();
            q.pop();

            if (visited[cur]) continue;
            visited[cur] = true;

            int w = bridge[cur];
            for (int i = cur; i < n; i += w) {
                if (!visited[i]) {
                    q.push(i);
                }
            }

            for (int i = cur - w; i >= 0; i -= w) {
                if (!visited[i]) {
                    q.push(i);
                }
            }
        }
        if (visited[to - 1]) break;
        answer++;
    }

    if (visited[to - 1]) cout << answer;
    else cout << -1;

    return 0;
}