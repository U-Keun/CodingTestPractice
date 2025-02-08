#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n, m, cur_idx;
vector<long long> arr;
vector<pair<int, int>> range_record;
vector<vector<int>> graph;

struct seg_tree {
    int size;
    vector<long long> tree, lazy;

    seg_tree(int size) {
        this->size = size;
        tree.resize(4 * size);
        lazy.resize(4 * size);
    }

    void update_lazy(int cur, int start, int end) {
        if (lazy[cur] == 0) return;
        if (start == end) {
            tree[cur] += lazy[cur];
        } else {
            lazy[cur * 2] += lazy[cur];
            lazy[cur * 2 + 1] += lazy[cur];
        }
        lazy[cur] = 0;
    }

    void update_range(int cur, int start, int end, int left, int right, int val) {
        update_lazy(cur, start, end);
        if (right < start || left > end) return;
        if (left <= start && end <= right) {
            if (start == end) {
                tree[cur] += val;
            } else {
                lazy[cur * 2] += val;
                lazy[cur * 2 + 1] += val;
            }
            return;
        }
        int mid = (start + end) / 2;
        update_range(cur * 2, start, mid, left, right, val);
        update_range(cur * 2 + 1, mid + 1, end, left, right, val);
    };

    long long query(int cur, int start, int end, int idx) {
        update_lazy(cur, start, end);
        if (idx < start || idx > end) return 0;
        if (start == end) return tree[cur];
        int mid = (start + end) / 2;
        return query(cur * 2, start, mid, idx) + query(cur * 2 + 1, mid + 1, end, idx);
    }
};

void rearrange_graph(int node) {
    range_record[node].first = ++cur_idx;
    for (auto &child : graph[node]) {
        if (range_record[child].first != -1) continue;
        rearrange_graph(child);
    }
    range_record[node].second = cur_idx;
}

int main() {
    FAST_IO

    cin >> n >> m;
    arr.resize(n);
    graph.resize(n);
    cin >> arr[0];
    REP(i, 1, n - 1) {
        cin >> arr[i];
        int parent; cin >> parent;
        parent--;
        graph[parent].push_back(i);
    }

    range_record.assign(n, { -1, -1 });
    cur_idx = -1;
    rearrange_graph(0);

    seg_tree seg(n);
    REP(i, 0, n - 1) seg.update_range(1, 0, n - 1, range_record[i].first, range_record[i].first, arr[i]);

    while (m--) {
        char q; cin >> q;
        if (q == 'p') {
            int a, x; cin >> a >> x;
            a--;
            seg.update_range(1, 0, n - 1, range_record[a].first + 1, range_record[a].second, x);
        } else { // q == 'u'
            int a; cin >> a;
            a--;
            cout << seg.query(1, 0, n - 1, range_record[a].first) << '\n';
        }
    }

    return EXIT_SUCCESS;
}