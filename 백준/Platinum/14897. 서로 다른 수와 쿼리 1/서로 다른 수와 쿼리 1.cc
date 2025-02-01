#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> compressed, frequency;
int n, sqrt_n, distinct;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_frequency(int num) {
    if (frequency[num] == 0) distinct++;
    frequency[num]++;
}

void remove_frequency(int num) {
    frequency[num]--;
    if (frequency[num] == 0) distinct--;
}

int main() {
    FAST_IO

    cin >> n;
    sqrt_n = sqrt(n);
    vector<int> arr(n);
    REP(i, 0, n - 1) cin >> arr[i];

    vector<int> tmp = arr;
    sort(tmp.begin(), tmp.end());
    tmp.erase(unique(tmp.begin(), tmp.end()), tmp.end());
    compressed.resize(n);
    REP(i, 0, n - 1) {
        int idx = lower_bound(tmp.begin(), tmp.end(), arr[i]) - tmp.begin();
        compressed[i] = idx;
    }

    frequency.resize(tmp.size() + 1, 0);
    distinct = 0;

    int m; cin >> m;
    vector<query> queries;
    REP(i, 0, m - 1) {
        int l, r; cin >> l >> r;
        l--; r--;
        queries.push_back({ i, l, r });
    }

    sort(queries.begin(), queries.end());

    int cur_left = 0, cur_right = -1;
    vector<long long> answer(m);
    for (const auto &q : queries) {
        int l = q.left, r = q.right;
        while (cur_right < r) add_frequency(compressed[++cur_right]);
        while (cur_right > r) remove_frequency(compressed[cur_right--]);
        while (cur_left < l) remove_frequency(compressed[cur_left++]);
        while (cur_left > l) add_frequency(compressed[--cur_left]);

        answer[q.idx] = distinct;
    }

    REP(i, 0, m - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}