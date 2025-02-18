#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define NUM_LIMIT 1000001

vector<int> arr, frequency;
int n, q, sqrt_n, distinct;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_frequency(int x) {
    frequency[x]++;
    if (frequency[x] == 3) distinct++;
}

void remove_frequency(int x) {
    if (frequency[x] == 3) distinct--;
    frequency[x]--;
}

int main() {
    FAST_IO

    cin >> n >> q;
    sqrt_n = sqrt(n);
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    frequency.resize(NUM_LIMIT, 0);
    distinct = 0;

    vector<query> queries;
    REP(i, 0, q - 1) {
        int l, r; cin >> l >> r;
        l--; r--;
        queries.push_back({ i, l, r });
    }

    sort(queries.begin(), queries.end());

    int cur_left = 0, cur_right = -1;
    vector<int> answer(q);
    for (const auto &query : queries) {
        int l = query.left, r = query.right;
        while (cur_right < r) add_frequency(arr[++cur_right]);
        while (cur_right > r) remove_frequency(arr[cur_right--]);
        while (cur_left < l) remove_frequency(arr[cur_left++]);
        while (cur_left > l) add_frequency(arr[--cur_left]);

        answer[query.idx] = distinct;
    }

    REP(i, 0, q - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}