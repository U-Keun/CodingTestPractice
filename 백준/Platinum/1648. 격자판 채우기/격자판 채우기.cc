#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)
#define MOD 9901

vector<int> dp;
int n, m;

void fill_col(int row_idx, int cur_mask, int next_mask, vector<int>& tmp) {
    if (row_idx == n) {
        tmp[next_mask] += dp[cur_mask] % MOD;
        tmp[next_mask] %= MOD;
        return;
    }

    if (cur_mask & (1 << row_idx)) fill_col(row_idx + 1, cur_mask, next_mask, tmp);
    else {
        fill_col(row_idx + 1, cur_mask, next_mask | (1 << row_idx), tmp);
        if (row_idx  + 1 < n && !(cur_mask & (1 << (row_idx + 1))))
            fill_col(row_idx + 2, cur_mask, next_mask, tmp);
    }
}

int main() {
    FAST_IO

    cin >> n >> m;
    dp.resize(1 << n, 0);
    dp[0] = 1;

    vector<int> tmp;
    while (m--) {
        tmp.assign(1 << n, 0);
        REP(mask, 0, (1 << n) - 1) {
            if (!dp[mask]) continue;
            fill_col(0, mask, 0, tmp);
        }
        dp = tmp;
    }

    cout << dp[0];

    return EXIT_SUCCESS;
}