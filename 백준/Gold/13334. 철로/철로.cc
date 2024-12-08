#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n; cin >> n;
    vector<pair<int, int>> pos(n);

    REP(i, 0, n - 1) {
        int h, o; cin >> h >> o;
        if (h <= o) pos[i] = { h, o};
        else pos[i] = { o, h };
    }

    int d; cin >> d;

    sort(pos.begin(), pos.end(), [](auto &a, auto &b) {
        return a.second < b.second;
    });

    priority_queue<int, vector<int>, greater<>> min_heap;

    int answer = 0;
    REP(i, 0, n - 1) {
        int cur_end = pos[i].second, limit = cur_end - d;
        min_heap.push(pos[i].first);

        while (!min_heap.empty() && min_heap.top() < limit) min_heap.pop();

        answer = max(answer, (int) min_heap.size());
    }

    cout << answer;

    return EXIT_SUCCESS;
}