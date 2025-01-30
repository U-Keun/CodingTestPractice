#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> arr, compressed, frequency;
int n, sqrt_n, distinct;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_frequency(int x) {
    if (frequency[x] == 2) distinct--;
    frequency[x]++;
    if (frequency[x] == 2) distinct++;
}

void remove_frequency(int x) {
    if (frequency[x] == 2) distinct--;
    frequency[x]--;
    if (frequency[x] == 2) distinct++;
}

int main() {
    FAST_IO

    int m;
    cin >> n >> m;
    sqrt_n = sqrt(n);
    arr.resize(n);
    frequency.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    vector<int> tmp = arr;
    sort(tmp.begin(), tmp.end());
    tmp.erase(unique(tmp.begin(), tmp.end()), tmp.end());
    compressed.resize(n);
    REP(i, 0, n - 1) {
        int idx = int(lower_bound(tmp.begin(), tmp.end(), arr[i]) - tmp.begin());
        compressed[i] = idx;
    }

    distinct = 0;

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
        while (cur_right < r) add_frequency(compressed[++cur_right]);
        while (cur_right > r) remove_frequency(compressed[cur_right--]);
        while (cur_left < l) remove_frequency(compressed[cur_left++]);
        while (cur_left > l) add_frequency(compressed[--cur_left]);

        answer[q.idx] = distinct;
    }

    REP(i, 0, m - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}