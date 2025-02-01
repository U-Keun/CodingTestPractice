#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define MAX_NUM 1000000

vector<int> arr, frequency;
int n, sqrt_n;
long long power;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_frequency(int num) {
    long long freq = frequency[num];
    power += (2 * freq + 1) * num;
    frequency[num]++;
}

void remove_frequency(int num) {
    long long freq = frequency[num];
    power -= (2 * freq - 1) * num;
    frequency[num]--;
}

int main() {
    FAST_IO

    int t;
    cin >> n >> t;
    sqrt_n = sqrt(n);
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    frequency.resize(MAX_NUM+ 1, 0);
    power = 0;

    vector<query> queries;
    REP(i, 0, t - 1) {
        int l, r; cin >> l >> r;
        l--; r--;
        queries.push_back({ i, l, r });
    }

    sort(queries.begin(), queries.end());

    int cur_left = 0, cur_right = -1;
    vector<long long> answer(t);
    for (const auto &q : queries) {
        int l = q.left, r = q.right, length = r - l + 1;
        while (cur_right < r) add_frequency(arr[++cur_right]);
        while (cur_right > r) remove_frequency(arr[cur_right--]);
        while (cur_left < l) remove_frequency(arr[cur_left++]);
        while (cur_left > l) add_frequency(arr[--cur_left]);

        answer[q.idx] = power;
    }

    REP(i, 0, t - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}