#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int cur_idx;
vector<vector<int>> graph;
vector<pair<int, int>> range_record;

struct seg_tree {
    vector<long long> upward_tree, downward_tree, lazy;

    seg_tree(int size) {
        upward_tree.resize(4 * size);
        downward_tree.resize(4 * size);
        lazy.resize(4 * size);
    }

    void update_lazy(int cur, int start, int end) {
        if (lazy[cur]) {
            if (start == end) {
                downward_tree[cur] += lazy[cur];
            } else {
                lazy[cur * 2] += lazy[cur];
                lazy[cur * 2 + 1] += lazy[cur];
            }
            lazy[cur] = 0;
        }
    }

    void update_point(int cur, int start, int end, int idx, int val) {
        if (idx < start || idx > end) return;
        if (start == end) {
            upward_tree[cur] += val;
            return;
        }
        int mid = (start + end) / 2;
        update_point(cur * 2, start, mid, idx, val);
        update_point(cur * 2 + 1, mid + 1, end, idx, val);
        upward_tree[cur] = upward_tree[cur * 2] + upward_tree[cur * 2 + 1];
    }

    void update_range(int cur, int start, int end, int left, int right, int val) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return;
        if (left <= start && end <= right) {
            if (start == end) {
                downward_tree[cur] += val;
            } else {
                lazy[cur * 2] += val;
                lazy[cur * 2 + 1] += val;
            }
            return;
        }
        int mid = (start + end) / 2;
        update_range(cur * 2, start, mid, left, right, val);
        update_range(cur * 2 + 1, mid + 1, end, left, right, val);
        downward_tree[cur] = downward_tree[cur * 2] + downward_tree[cur * 2 + 1];
    }

    long long query_point(int cur, int start, int end, int idx) {
        update_lazy(cur, start, end);
        if (idx < start || idx > end) return 0;
        if (start == end) return downward_tree[cur];
        int mid = (start + end) / 2;
        return query_point(cur * 2, start, mid, idx) +
            query_point(cur * 2 + 1, mid + 1, end, idx);
    }

    long long query_range(int cur, int start, int end, int left, int right) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return upward_tree[cur];
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

    int n, m; cin >> n >> m;
    graph.resize(n);
    REP(i, 0, n - 1) {
        int p; cin >> p;
        if (i == 0) continue;
        graph[p - 1].push_back(i);
    }

    range_record.resize(n, { -1, -1 });
    cur_idx = -1;
    euler(0);

    seg_tree st(n);
    bool upward = false;
    while (m--) {
        int q; cin >> q;
        if (q == 1) {
            int i, w; cin >> i >> w;
            i--;
            if (upward) st.update_point(1, 0, n - 1, range_record[i].first, w);
            else {
                int from = range_record[i].first, to = range_record[i].second;
                st.update_range(1, 0, n - 1, from, to, w);
            }
        } else if (q == 2) {
            int i; cin >> i;
            i--;
            int from = range_record[i].first, to = range_record[i].second;
            long long answer = st.query_point(1, 0, n - 1, from) +
                    st.query_range(1, 0, n - 1, from, to);
            cout << answer << '\n';
        } else { // q == 3
            upward = !upward;
        }
    }

    return EXIT_SUCCESS;
}