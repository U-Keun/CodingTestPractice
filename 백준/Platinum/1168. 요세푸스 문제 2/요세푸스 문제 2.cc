#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

struct seg_tree {
    vector<int> tree;

    seg_tree(int n) {
        tree.resize(4 * n);
        build(1, 0, n - 1);
    }

    void build(int cur, int start, int end) {
        if (start == end) {
            tree[cur] = 1;
            return;
        }
        int mid = (start + end) / 2;
        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }

    void update(int cur, int start, int end, int idx, int val) {
        if (idx < start || idx > end) return;
        if (start == end) {
            tree[cur] = val;
            return;
        }
        int mid = (start + end) / 2;
        update(cur * 2, start, mid, idx, val);
        update(cur * 2 + 1, mid + 1, end, idx, val);
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }

    int query(int cur, int start, int end, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[cur];
        int mid = (start + end) / 2;
        return query(cur * 2, start, mid, left, right) +
            query(cur * 2 + 1, mid + 1, end, left, right);
    }

    int find_kth_one(int cur, int start, int end, int k) {
        if (start == end) return start;
        int left = tree[cur * 2], mid = (start + end) / 2;
        if (left >= k) return find_kth_one(cur * 2, start, mid, k);
        else return find_kth_one(cur * 2 + 1, mid + 1, end, k - left);
    }
};

int main() {
    FAST_IO

    int n, k; cin >> n >> k;
    seg_tree st(n);

    int cur_idx = 0, rest = n;
    vector<int> answer;
    while (true) {
        int next = (k - 1) % rest + 1,
            right = st.query(1, 0, n - 1, cur_idx, n - 1);

        int remove;
        if (right >= next) {
            int left = st.query(1, 0, n - 1, 0, cur_idx - 1);
            remove = st.find_kth_one(1, 0, n - 1, left + next);
        } else remove = st.find_kth_one(1, 0, n - 1, next - right);

        st.update(1, 0, n - 1, remove, 0);
        answer.push_back(remove + 1);
        rest--;
        if (!rest) break;

        if (st.query(1, 0, n - 1, remove + 1, n - 1)) {
            int left = st.query(1, 0, n - 1, 0, remove);
            cur_idx = st.find_kth_one(1, 0, n - 1, left + 1);
        } else cur_idx = st.find_kth_one(1, 0, n - 1, 1);
    }

    cout << '<';
    REP(i, 0, n - 1) {
        cout << answer[i];
        if (i < n - 1) cout << ", ";
    }
    cout << '>';

    return EXIT_SUCCESS;
}