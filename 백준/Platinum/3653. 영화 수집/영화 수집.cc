#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int cur_top;
vector<int> heights;
vector<int> seg_tree;

void update(int cur, int start, int end, int idx, int val) {
    if (idx < start || idx > end) return;
    if (start == end) {
        seg_tree[cur] += val;
        return;
    }
    int mid = (start + end) / 2;
    update(cur * 2, start, mid, idx, val);
    update(cur * 2 + 1, mid + 1, end, idx, val);
    seg_tree[cur] = seg_tree[cur * 2] + seg_tree[cur * 2 + 1];
}

int query(int cur, int start, int end, int left, int right) {
    if (start > right || end < left) return 0;
    if (start >= left && end <= right) return seg_tree[cur];
    int mid = (start + end) / 2;
    return query(cur * 2, start, mid, left, right) +
        query(cur * 2 + 1, mid + 1, end, left, right);
}

int main() {
    FAST_IO

    int t; cin >> t;
    while (t-- > 0) {
        int n, m; cin >> n >> m;
        heights.resize(n, 0);
        REP(i, 0, n - 1) heights[i] = n - i;
        cur_top = n;
        seg_tree.assign(4 * (n + m), 0);
        REP(i, 1, n) update(1, 1, n + m, i, 1);

        int q;
        REP(i, 0, m - 1) {
            cin >> q;
            int res = query(1, 1, n + m, heights[q - 1] + 1, cur_top++);
            update(1, 1, n + m, heights[q - 1], -1);
            update(1, 1, n + m, cur_top, 1);
            heights[q - 1] = cur_top;
            cout << res << ' ';
        }

        cout << '\n';
    }

    return EXIT_SUCCESS;
}