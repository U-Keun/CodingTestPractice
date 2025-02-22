#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define MAX_NUM 1048576

vector<int> arr;
vector<long long> record;
int n, k, sqrt_n;
long long pair_count;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_entry(int idx) {
    int val = arr[idx];
    pair_count += record[val ^ k];
    record[val]++;
}

void remove_entry(int idx) {
    int val = arr[idx];
    record[val]--;
    pair_count -= record[val ^ k];
}

int main() {
    FAST_IO

    cin >> n >> k;
    sqrt_n = sqrt(n);
    arr.resize(n + 1);
    record.resize(MAX_NUM);
    REP(i, 1, n) {
        int val; cin >> val;
        arr[i] = arr[i - 1] ^ val;
    }

    int m; cin >> m;
    vector<query> queries;
    REP(i, 0, m - 1) {
        int l, r; cin >> l >> r;
        queries.push_back({ i, --l, r });
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