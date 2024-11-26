#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<vector<int>> power = {
        { 0, 2, 2, 2, 2 },
        { 0, 1, 3, 4, 3 },
        { 0, 3, 1, 3, 4 },
        { 0, 4, 3, 1, 3 },
        { 0, 3, 4, 3, 1 },
};

int main() {
    FAST_IO

    int num, idx = 0;
    vector<vector<vector<int>>> dp(100001, vector<vector<int>>(5, vector<int>(5, 400001)));
    dp[0][0][0] = 0;
    cin >> num;
    while (num != 0) {
        idx++;
        REP(r, 0, 4) {
            REP(l, 0, 4) dp[idx][num][r] = min(dp[idx][num][r], dp[idx - 1][l][r] + power[l][num]);
        }

        REP(l, 0, 4) {
            REP(r, 0, 4) dp[idx][l][num] = min(dp[idx][l][num], dp[idx - 1][l][r] + power[r][num]);
        }

        cin >> num;
    }

    int answer = 400001;
    REP(i, 0, 4) {
        REP(j, 0, 4) {
            answer = min(answer, dp[idx][i][j]);
        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}