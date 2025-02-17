#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

struct seg_tree {
    vector<int> tree;

    seg_tree(int size) {
        tree.resize(4 * size);
    }

    void update_point(int cur, int start, int end, int idx, int val) {
        if (idx < start || end < idx) return;
        if (start == end) {
            tree[cur] += val;
            return;
        }
        int mid = (start + end) / 2;
        update_point(cur * 2, start, mid, idx, val);
        update_point(cur * 2 + 1, mid + 1, end, idx, val);
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }

    int query_range(int cur, int start, int end, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[cur];
        int mid = (start + end) / 2;
        return query_range(cur * 2, start, mid, left, right) +
                query_range(cur * 2 + 1, mid + 1, end, left, right);
    }

    int find_kth(int cur, int start, int end, int k) {
        if (start == end) return start;
        int left = tree[cur * 2], mid = (start + end) / 2;
        if (left >= k) return find_kth(cur * 2, start, mid, k);
        else return find_kth(cur * 2 + 1, mid + 1, end, k - left);
    }
};

int main() {
    FAST_IO

    seg_tree st(2000000);

    int n; cin >> n;
    while (n--) {
        int t, x; cin >> t >> x;
        if (t == 1) {
            st.update_point(1, 1, 2000000, x, 1);
        } else { // t == 2
            int remove = st.find_kth(1, 1, 2000000, x);
            cout << remove << '\n';
            st.update_point(1, 1, 2000000, remove, -1);
        }
    }

    return EXIT_SUCCESS;
}