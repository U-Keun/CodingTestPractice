#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n, m, cur_idx;
vector<long long> arr;
vector<pair<int, int>> range_record;
vector<vector<int>> graph;
vector<int> reverse_map;

struct seg_tree {
    int size;
    vector<long long> lazy;

    seg_tree(int size) {
        this->size = size;
        lazy.resize(4 * size);
    }

    void update_lazy(int cur, int start, int end) {
        if (lazy[cur] == 0) return;
        if (start == end) {
            arr[reverse_map[start]] += lazy[cur];
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
                arr[reverse_map[start]] += val;
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

    void prepare_query(int cur, int start, int end, int idx) {
        update_lazy(cur, start, end);
        if (idx < start || idx > end || start == end) return;
        int mid = (start + end) / 2;
        prepare_query(cur * 2, start, mid, idx);
        prepare_query(cur * 2 + 1, mid + 1, end, idx);
    }

    long long query(int cur, int start, int end, int idx) {
        update_lazy(cur, start, end);
        if (idx < start || idx > end) return 0;
        if (start == end) return arr[reverse_map[start]];
        int mid = (start + end) / 2;
        return query(cur * 2, start, mid, idx) + query(cur * 2 + 1, mid + 1, end, idx);
    }
};

void rearrange_graph(int node) {
    range_record[node].first = ++cur_idx;
    reverse_map[cur_idx] = node;
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
    reverse_map.resize(n);
    REP(i, 0, n - 1) {
        int parent; cin >> parent;
        if (i == 0) continue;
        parent--;
        graph[parent].push_back(i);
    }

    range_record.assign(n, { -1, -1 });
    cur_idx = -1;
    rearrange_graph(0);

    seg_tree seg(n);

    while (m--) {
        int q; cin >> q;
        if (q == 1) {
            int i, w; cin >> i >> w;
            i--;
            seg.update_range(1, 0, n - 1, range_record[i].first, range_record[i].second, w);
        } else { // q == 2
            int i; cin >> i;
            i--;
            seg.prepare_query(1, 0, n - 1, range_record[i].first);
            cout << arr[i] << '\n';
        }
    }

    return EXIT_SUCCESS;
}