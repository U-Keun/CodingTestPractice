#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int cur_idx;
vector<vector<int>> graph;
vector<pair<int, int>> range_record;

struct seg_tree {
    vector<int> tree, lazy;

    seg_tree(int size) {
        tree.resize(4 * size);
        lazy.resize(4 * size, -1);
        build(1, 0, size - 1);
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

    void update_lazy(int cur, int start, int end) {
        if (lazy[cur] != -1) {
            tree[cur] = (end - start + 1) * lazy[cur];
            if (start != end) {
                lazy[cur * 2] = lazy[cur];
                lazy[cur * 2 + 1] = lazy[cur];
            }
            lazy[cur] = -1;
        }
    }

    void update_range(int cur, int start, int end, int left, int right, int val) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return;
        if (left <= start && end <= right) {
            tree[cur] = (end - start + 1) * val;
            if (start != end) {
                lazy[cur * 2] = val;
                lazy[cur * 2 + 1] = val;
            }
            return;
        }
        int mid = (start + end) / 2;
        update_range(cur * 2, start, mid, left, right, val);
        update_range(cur * 2 + 1, mid + 1, end, left, right, val);
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }

    long long query_range(int cur, int start, int end, int left, int right) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[cur];
        int mid = (start + end) / 2;
        return query_range(cur * 2, start, mid, left, right) +
                query_range(cur * 2 + 1, mid + 1, end, left, right);
    }
};

void euler(int node) {
    range_record[node].first = ++cur_idx;
    for (const auto &child : graph[node]) {
        euler(child);
    }
    range_record[node].second = cur_idx;
}

int main() {
    FAST_IO

    int n; cin >> n;
    graph.resize(n);
    REP(i, 0, n - 1) {
        int p; cin >> p;
        if (i == 0) continue;
        graph[p - 1].push_back(i);
    }

    range_record.resize(n, { -1, -1 });
    cur_idx = -1;
    euler(0);

    int m; cin >> m;
    seg_tree st(n);
    while (m--) {
        int q, i; cin >> q >> i;
        i--;
        int from = range_record[i].first + 1,
            to = range_record[i].second;
        if (q == 3) {
            cout << st.query_range(1, 0, n - 1, from, to) << '\n';
        } else { // q == 1 or q == 2
            st.update_range(1, 0, n - 1, from, to, q % 2);
        }
    }

    return EXIT_SUCCESS;
}