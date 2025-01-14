#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> arr;
vector<long long> seg_tree;

void generate_tree(int cur, int start, int end) {
    if (start == end) {
        seg_tree[cur] = arr[start];
        return;
    }

    int mid = (start + end) / 2;
    generate_tree(cur * 2, start, mid);
    generate_tree(cur * 2 + 1, mid + 1, end);
    seg_tree[cur] = seg_tree[cur * 2] + seg_tree[cur * 2 + 1];
}

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

long long query(int cur, int start, int end, int left, int right) {
    if (start > right || end < left) return 0;
    if (start >= left && end <= right) return seg_tree[cur];
    int mid = (start + end) / 2;
    return query(cur * 2, start, mid, left, right) +
        query(cur * 2 + 1, mid + 1, end, left, right);
}

int main() {
    FAST_IO

    int n; cin >> n;
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];
    seg_tree.resize(4 * n);
    generate_tree(1, 0, n - 1);

    int m, q, idx = 0; cin >> m;
    vector<pair<int, int>> updates;
    priority_queue<tuple<int, int, int, int>,
        vector<tuple<int, int, int, int>>,
        greater<>> queries;
    while (m-- > 0) {
        cin >> q;
        if (q == 1) {
            int a, b; cin >> a >> b;
            updates.push_back({ a, b });
        } else { // q == 2
            int a, b, c; cin >> a >> b >> c;
            queries.emplace(a, idx++, b, c);
        }
    }

    vector<long long> answer(idx);
    int updated = 0;
    REP(i, 0, idx - 1) {
        int time = get<0>(queries.top());
        while (updated < time) {
            int j = updates[updated].first - 1, diff = updates[updated].second - arr[j];
            update(1, 0, n - 1, j, diff);
            arr[j] = updates[updated].second;
            updated++;
        }
        int k = get<1>(queries.top()),
            l = get<2>(queries.top()),
            r = get<3>(queries.top());
        long long val = query(1, 0, n - 1, l - 1, r - 1);
        answer[k] = val;
        queries.pop();
    }

    for (long long num : answer) cout << num << '\n';

    return EXIT_SUCCESS;
}