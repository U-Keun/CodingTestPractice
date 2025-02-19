#include <iostream>
#include <algorithm>
#include <vector>
#include <list>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<long long> arr;
vector<int> dist_freq;
vector<list<int>> deq;
int n, k, sqrt_n, max_dist;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_entry(int idx, bool right) {
    int num = arr[idx];
    auto &dq = deq[num];
    if (!dq.empty()) {
        int val = dq.back() - dq.front();
        dist_freq[val]--;
        if (val == max_dist && dist_freq[val] == 0) {
            while (max_dist >= 0 && dist_freq[max_dist] == 0) {
                max_dist--;
            }
        }
    }

    if (right) dq.push_back(idx);
    else dq.push_front(idx);

    int val = dq.back() - dq.front();
    dist_freq[val]++;
    max_dist = max(max_dist, val);
}

void remove_entry(int idx, bool right) {
    int num = arr[idx];
    auto &dq = deq[num];
    int val = dq.back() - dq.front();
    dist_freq[val]--;
    if (val == max_dist && dist_freq[val] == 0) {
        while (max_dist >= 0 && dist_freq[max_dist] == 0) {
            max_dist--;
        }
    }

    if (right) dq.pop_back();
    else dq.pop_front();

    if (!dq.empty()) {
        val = dq.back() - dq.front();
        dist_freq[val]++;
        max_dist = max(max_dist, val);
    }
}

int main() {
    FAST_IO

    cin >> n >> k;
    sqrt_n = sqrt(n + 1);
    arr.resize(n + 1);
    dist_freq.resize(n + 1, 0);
    deq.resize(k + 1);
    REP(i, 1, n) {
        int input; cin >> input;
        arr[i] = (arr[i - 1] + input) % k;
    }

    int m; cin >> m;
    vector<query> queries;
    REP(i, 0, m - 1) {
        int l, r; cin >> l >> r;
        queries.push_back({ i, l - 1, r });
    }

    sort(queries.begin(), queries.end());

    int cur_left = 1, cur_right = 0;
    vector<int> answer(m);
    for (const auto &q : queries) {
        int l = q.left, r = q.right;
        while (cur_right < r) add_entry(++cur_right, true);
        while (cur_right > r) remove_entry(cur_right--, true);
        while (cur_left < l) remove_entry(cur_left++, false);
        while (cur_left > l) add_entry(--cur_left, false);

        answer[q.idx] = max_dist;
    }

    REP(i, 0, m - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}