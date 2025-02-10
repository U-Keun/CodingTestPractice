#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int cur_idx;
vector<vector<int>> graph;
vector<pair<int, int>> range_record;
vector<int> arr, reverse_idx;

struct seg_tree {
    int size;
    vector<int> tree, lazy;

    seg_tree(int n) {
        size = n;
        tree.resize(4 * n, 0);
        lazy.resize(4 * n, 0);
    }

    void build(int cur, int start, int end) {
        if (start == end) {
            tree[cur] = arr[reverse_idx[start]];
            return;
        }
        int mid = (start + end) / 2;
        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);
        tree[cur] = tree[cur * 2] ^ tree[cur * 2 + 1];
    }

    void update_lazy(int cur, int start, int end) {
        if (lazy[cur] != 0) {
            int l = end - start + 1;
            if (l % 2 == 1) tree[cur] ^= lazy[cur];
            if (start != end) {
                lazy[cur * 2] ^= lazy[cur];
                lazy[cur * 2 + 1] ^= lazy[cur];
            }
            lazy[cur] = 0;
        }
    }

    void update_range(int cur, int start, int end, int left, int right, int val) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return;
        if (left <= start && end <= right) {
            lazy[cur] = val;
            update_lazy(cur, start, end);
            return;
        }
        int mid = (start + end) / 2;
        update_range(cur * 2, start, mid, left, right, val);
        update_range(cur * 2 + 1, mid + 1, end, left, right, val);
        tree[cur] = tree[cur * 2] ^ tree[cur * 2 + 1];
    }

    int query(int cur, int start, int end, int left, int right) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[cur];
        int mid = (start + end) / 2;
        return query(cur * 2, start, mid, left, right) ^ query(cur * 2 + 1, mid + 1, end, left, right);
    }
};


void circuit(int node) {
    range_record[node].first = ++cur_idx;
    reverse_idx[cur_idx] = node;
    for (const auto &nbd : graph[node]) {
        if (range_record[nbd].first != -1) continue;
        circuit(nbd);
    }
    range_record[node].second = cur_idx;
}

int main() {
    FAST_IO

    int n, m; cin >> n >> m;
    graph.resize(n);
    REP(i, 1, n - 1) {
        int u, v; cin >> u >> v;
        u--; v--;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    range_record.resize(n, { -1, -1 });
    reverse_idx.resize(n);
    cur_idx = -1;
    circuit(0);

    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];
    seg_tree st(n);
    st.build(1, 0, n - 1);

    while (m--) {
        int q; cin >> q;
        if (q == 1) {
            int x; cin >> x;
            x--;
            cout << st.query(1, 0, n - 1, range_record[x].first, range_record[x].second) << '\n';
        } else { // q == 2
            int x, y; cin >> x >> y;
            x--;
            st.update_range(1, 0, n - 1, range_record[x].first, range_record[x].second, y);

        }
    }

    return EXIT_SUCCESS;
}