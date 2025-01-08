#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> compressed, seg_tree;

void update(int cur, int start, int end, int idx, int val) {
    if (idx < start || idx > end) return;
    seg_tree[cur] += val;
    if (start == end) return;
    int mid = (start + end) / 2;
    update(cur * 2, start, mid, idx, val);
    update(cur * 2 + 1, mid + 1, end, idx, val);
}

int query(int cur, int start, int end, int left, int right) {
    if (right < start || left > end) return 0;
    if (left <= start && end <= right) return seg_tree[cur];
    int mid = (start + end) / 2;
    return query(cur * 2, start, mid, left, right) +
        query(cur * 2 + 1, mid + 1, end, left, right);
}

int main() {
    FAST_IO

    int n; cin >> n;
    priority_queue<pair<int, int>> pq;
    int num;
    REP(i, 0, n - 1) {
        cin >> num;
        pq.emplace(num, i);
    }

    compressed.resize(n);
    int idx = n;
    while (!pq.empty()) {
        compressed[pq.top().second] = idx--;
        pq.pop();
    }
    
    seg_tree.resize(4 * n);
    vector<int> result(n);

    REP(i, 0, n - 1) {
        int v = compressed[i];
        result[i] = query(1, 1, n, 1, v - 1);

        update(1, 1, n, v, 1);
    }

    REP(i, 0, n - 1) {
        cout << (i + 1 - result[i]) << '\n';
    }

    return EXIT_SUCCESS;
}