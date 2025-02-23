#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> arr, compressed, fenwick;
int n, m, sqrt_n, max_num;
long long pair_count;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void update(int idx, int val) {
    while (idx <= max_num) {
        fenwick[idx] += val;
        idx += idx & -idx;
    }
}

int prefix_sum(int idx) {
    int sum = 0;
    while (idx > 0) {
        sum += fenwick[idx];
        idx -= idx & -idx;
    }
    return sum;
}

int query_range(int left, int right) {
    return prefix_sum(right) - prefix_sum(left - 1);
}

void add_left(int idx) {
    int val = compressed[idx];
    pair_count += query_range(1, val - 1);
    update(val, 1);
}

void add_right(int idx) {
    int val = compressed[idx];
    pair_count += query_range(val + 1, max_num);
    update(val, 1);
}

void remove_left(int idx) {
    int val = compressed[idx];
    pair_count -= query_range(1, val - 1);
    update(val, -1);
}

void remove_right(int idx) {
    int val = compressed[idx];
    pair_count -= query_range(val + 1, max_num);
    update(val, -1);
}

int main() {
    FAST_IO

    cin >> n >> m;
    sqrt_n = sqrt(n);
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    vector<int> tmp = arr;
    sort(tmp.begin(), tmp.end());
    tmp.erase(unique(tmp.begin(), tmp.end()), tmp.end());
    max_num = tmp.size();
    fenwick.resize(max_num + 1);
    compressed.resize(n + 1);
    REP(i, 0, n - 1) {
        int idx = lower_bound(tmp.begin(), tmp.end(), arr[i]) - tmp.begin();
        compressed[i + 1] = idx + 1;
    }

    vector<query> queries;
    REP(i, 0, m - 1) {
        int l, r; cin >> l >> r;
        queries.push_back({ i, l, r });
    }

    sort(queries.begin(), queries.end());

    int cur_left = 1, cur_right = 0;
    pair_count = 0;
    vector<long long> answer(m);
    for (const auto &q : queries) {
        int l = q.left, r = q.right;
        while (cur_right < r) add_right(++cur_right);
        while (cur_right > r) remove_right(cur_right--);
        while (cur_left < l) remove_left(cur_left++);
        while (cur_left > l) add_left(--cur_left);

        answer[q.idx] = pair_count;
    }

    REP(i, 0, m - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}