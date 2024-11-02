#include <iostream>
#include <queue>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;
    queue<pair<int, int>> q;

    int idx = 0, needs;
    for (int i = 0; i < n; i++) {
        cin >> needs;
        q.push({ needs, idx++ });
    }

    int time = 0;
    vector<int> answer(n);
    while (!q.empty()) {
        int rest = q.front().first, cur = q.front().second;
        q.pop();
        time++;

        rest--;
        if (rest == 0) {
            answer[cur] = time;
        } else {
            q.push({ rest, cur });
        }
    }

    for (int num : answer) cout << num << ' ';

    return 0;
}