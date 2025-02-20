#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define MAX_NUM 100000

vector<int> arr, fenwick;
int n, k, sqrt_n;
long long pair_count;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void update(int idx, int val) {
    while (idx <= MAX_NUM) {
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

void add_entry(int idx) {
    int val = arr[idx];
    pair_count += prefix_sum(min(MAX_NUM, val + k)) - prefix_sum(max(1, val - k) - 1);
    update(val, 1);
}

void remove_entry(int idx) {
    int val = arr[idx];
    update(val, -1);
    pair_count -= prefix_sum(min(MAX_NUM, val + k)) - prefix_sum(max(1, val - k) - 1);
}

int main() {
    FAST_IO

    cin >> n >> k;
    sqrt_n = sqrt(n);
    arr.resize(n + 1);
    fenwick.resize(MAX_NUM + 1);
    REP(i, 1, n) cin >> arr[i];

    int m; cin >> m;
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
        while (cur_right < r) add_entry(++cur_right);
        while (cur_right > r) remove_entry(cur_right--);
        while (cur_left < l) remove_entry(cur_left++);
        while (cur_left > l) add_entry(--cur_left);

        answer[q.idx] = pair_count;
    }

    REP(i, 0, m - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}