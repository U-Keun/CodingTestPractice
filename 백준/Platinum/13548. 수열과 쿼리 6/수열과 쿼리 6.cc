#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define NUM_LIMIT 1000001

vector<int> arr, frequency, frequency_record;
int n, sqrt_n, max_frequency;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_frequency(int num) {
    int freq = frequency[num];
    if (frequency_record[freq]) frequency_record[freq]--;
    frequency[num]++;
    frequency_record[freq + 1]++;
    if (max_frequency < freq + 1) max_frequency = freq + 1;
}

void remove_frequency(int num) {
    int freq = frequency[num];
    if (frequency_record[freq]) frequency_record[freq]--;
    frequency[num]--;
    frequency_record[freq - 1]++;
    if (!frequency_record[max_frequency]) max_frequency--;
}

int main() {
    FAST_IO

    cin >> n;
    sqrt_n = sqrt(n);
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    frequency.resize(NUM_LIMIT, 0);
    frequency_record.resize(n + 1);
    max_frequency = 0;

    vector<query> queries;
    int m; cin >> m;
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
        while (cur_right < r) add_frequency(arr[++cur_right]);
        while (cur_right > r) remove_frequency(arr[cur_right--]);
        while (cur_left < l) remove_frequency(arr[cur_left++]);
        while (cur_left > l) add_frequency(arr[--cur_left]);

        answer[q.idx] = max_frequency;
    }

    REP(i, 0, m - 1) cout << answer[i] << '\n';

    return EXIT_SUCCESS;
}