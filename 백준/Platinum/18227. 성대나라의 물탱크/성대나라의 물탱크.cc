#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int cur_idx;
vector<vector<int>> graph;
vector<pair<int, int>> range_record;
vector<long long> heights;

struct seg_tree {
    vector<int> tree;

    seg_tree(int size) {
        tree.resize(4 * size);
    }

    void update_point(int cur, int start, int end, int idx, int val) {
        if (idx < start || idx > end) return;
        if (start == end) {
            tree[cur] += val;
            return;
        }
        int mid = (start + end) / 2;
        update_point(cur * 2, start, mid, idx, val);
        update_point(cur * 2 + 1, mid + 1, end, idx, val);
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }

    long long query_range(int cur, int start, int end, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[cur];
        int mid = (start + end) / 2;
        return query_range(cur * 2, start, mid, left, right) +
                query_range(cur * 2 + 1, mid + 1, end, left, right);
    }
};

void euler(int node, int height) {
    range_record[node].first = ++cur_idx;
    heights[node] = height;
    for (const auto &nbd : graph[node]) {
        if (range_record[nbd].first != -1) continue;
        euler(nbd, height + 1);
    }
    range_record[node].second = cur_idx;
}

int main() {
    FAST_IO

    int n, c; cin >> n >> c;
    graph.resize(n);
    REP(i, 1, n - 1) {
        int u, v; cin >> u >> v;
        u--; v--;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    range_record.resize(n, { -1, -1 });
    heights.resize(n);
    cur_idx = -1;
    euler(c - 1, 1);

    int m; cin >> m;
    seg_tree st(n);
    while (m--) {
        int q, i; cin >> q >> i;
        i--;
        if (q == 1) st.update_point(1, 0, n - 1, range_record[i].first, 1);
        else { // q == 2
            int from = range_record[i].first,
                to = range_record[i].second;
            cout << (heights[i] * st.query_range(1, 0, n - 1, from, to)) << '\n';
        }
    }

    return EXIT_SUCCESS;
}