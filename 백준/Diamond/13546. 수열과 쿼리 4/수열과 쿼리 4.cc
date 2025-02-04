#include <iostream>
#include <algorithm>
#include <vector>
#include <list>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> arr, dist_freq, bucket;
vector<list<int>> deq;
int n, k, sqrt_n;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

int find_max_dist() {
    for (int i = n / sqrt_n; i >= 0; i--) {
        if (bucket[i] == 0) continue;
        for (int j = sqrt_n - 1; j >= 0; j--) {
            int dist = i * sqrt_n + j;
            if (dist >= n) continue;
            if (dist_freq[dist] > 0) {
                return dist;
            }
        }
    }
    return 0;
}

void add_entry(int idx, bool right) {
    int num = arr[idx];
    auto &dq = deq[num - 1];
    if (!dq.empty()) {
        int val = dq.back() - dq.front();
        dist_freq[val]--;
        bucket[val / sqrt_n]--;
    }

    if (right) dq.push_back(idx);
    else dq.push_front(idx);

    int val = dq.back() - dq.front();
    dist_freq[val]++;
    bucket[val / sqrt_n]++;
}

void remove_entry(int idx, bool right) {
    int num = arr[idx];
    auto &dq = deq[num - 1];
    int val = dq.back() - dq.front();
    dist_freq[val]--;
    bucket[val / sqrt_n]--;

    if (right) dq.pop_back();
    else dq.pop_front();

    if (!dq.empty()) {
        val = dq.back() - dq.front();
        dist_freq[val]++;
        bucket[val / sqrt_n]++;
    }
}

int main() {
    FAST_IO

    cin >> n >> k;
    sqrt_n = sqrt(n);
    arr.resize(n);
    dist_freq.resize(n, 0);
    deq.resize(k);
    bucket.resize(n / sqrt_n + 1, 0);
    REP(i, 0, n - 1) cin >> arr[i];

    int m; cin >> m;
    vector<query> queries;
    REP(i, 0, m - 1) {
        int l, r; cin >> l >> r;
        l--; r--;
        queries.push_back({ i, l, r });
    }

    sort(queries.begin(), queries.end());

    int cur_left = 0, cur_right = -1;
    vector<int> answer(m);
    for (const auto &q : queries) {
        int l = q.left, r = q.right;
        while (cur_right < r) add_entry(++cur_right, true);
        while (cur_right > r) remove_entry(cur_right--, true);
        while (cur_left < l) remove_entry(cur_left++, false);
        while (cur_left > l) add_entry(--cur_left, false);

        answer[q.idx] = find_max_dist();
    }

    REP(i, 0, m - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}