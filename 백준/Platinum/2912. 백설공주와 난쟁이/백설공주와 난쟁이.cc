#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

vector<int> arr, frequency, freq_record;
int n, c, sqrt_n, max_frequency;

struct query {
    int idx, left, right;
    bool operator < (const query &x) const {
        if (left / sqrt_n != x.left / sqrt_n) return left / sqrt_n < x.left / sqrt_n;
        return right < x.right;
    }
};

void add_frequency(int num) {
    int freq = frequency[num];
    freq_record[freq++]--;
    frequency[num]++;
    freq_record[freq]++;
    if (max_frequency < frequency[num]) {
        max_frequency = frequency[num];
    }
}

void remove_frequency(int num) {
    int freq = frequency[num];
    freq_record[freq--]--;
    frequency[num]--;
    freq_record[freq]++;
    if (!freq_record[max_frequency]) {
        max_frequency--;
    }
}

int main() {
    FAST_IO

    cin >> n >> c;
    sqrt_n = sqrt(n);
    arr.resize(n);
    REP(i, 0, n - 1) cin >> arr[i];

    frequency.resize(c + 1, 0);
    freq_record.resize(n + 1, 0);
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
    vector<int> answer(m, -1);
    for (const auto &q : queries) {
        int l = q.left, r = q.right, length = r - l + 1;
        while (cur_right < r) add_frequency(arr[++cur_right]);
        while (cur_right > r) remove_frequency(arr[cur_right--]);
        while (cur_left < l) remove_frequency(arr[cur_left++]);
        while (cur_left > l) add_frequency(arr[--cur_left]);

        if (max_frequency > length / 2 && freq_record[max_frequency] == 1) {
            REP(i, 1, c) {
                if (frequency[i] == max_frequency) {
                    answer[q.idx] = i;
                    break;
                }
            }
        }
    }

    REP(i, 0, m - 1) {
        if (answer[i] < 0) cout << "no\n";
        else cout << "yes " << answer[i] << '\n';
    };

    return EXIT_SUCCESS;
}