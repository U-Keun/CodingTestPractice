#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

struct seg_tree {
    int size;
    vector<int> tree;
    vector<int> lazy;

    seg_tree(int n) {
        size = n;
        tree.resize(4 * n, 1);
        lazy.resize(4 * n);
    }

    void update_lazy(int cur, int start, int end) {
        if (lazy[cur]) {
            tree[cur] = lazy[cur];
            if (start != end) {
                lazy[cur * 2] = lazy[cur];
                lazy[cur * 2 + 1] = lazy[cur];
            }
            lazy[cur] = 0;
        }
    }

    void update_internal(int cur, int start, int end, int left, int right, int val) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return;
        if (left <= start && end <= right) {
            int mask = 1 << (val - 1);
            tree[cur] = mask;
            if (start != end) {
                lazy[cur * 2] = mask;
                lazy[cur * 2 + 1] = mask;
            }
            return;
        }
        int mid = (start + end) / 2;
        update_internal(cur * 2, start, mid, left, right, val);
        update_internal(cur * 2 + 1, mid + 1, end, left, right, val);
        tree[cur] = tree[cur * 2] | tree[cur * 2 + 1];
    }

    int get_set(int cur, int start, int end, int left, int right) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[cur];
        int mid = (start + end) / 2;
        int left_set = get_set(cur * 2, start, mid, left, right),
            right_set = get_set(cur * 2 + 1, mid + 1, end, left, right);
        return left_set | right_set;
    }

    void update(int left, int right, int val) {
        update_internal(1, 1, size, left, right, val);
    }

    int get_distinct_count(int left, int right) {
        int result = get_set(1, 1, size, left, right);
        return __builtin_popcount(result);
    }
};

int main() {
    FAST_IO

    int n, t, q; cin >> n >> t >> q;
    seg_tree s(n);
    while (q--) {
        char c; cin >> c;
        int a, b; cin >> a >> b;
        if (a > b) swap(a, b);
        if (c == 'C') {
            int v; cin >> v;
            s.update(a, b, v);
        } else {
            cout << s.get_distinct_count(a, b) << '\n';
        }
    }

    return EXIT_SUCCESS;
}