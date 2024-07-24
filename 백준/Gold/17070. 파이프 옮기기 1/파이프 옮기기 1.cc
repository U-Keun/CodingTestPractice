#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;

    vector<vector<int>> house(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> house[i][j];
        }
    }

    vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(3, 0))); // 각 지점에서 길이 3짜리 배열은 각각 가로로, 대각선으로, 세로로 놓인 경우들을 의미한다.

    for (int i = 1; i < n; i++) {
        if (house[0][i] == 1) break;
        dp[0][i][0] = 1; // 집의 가장 윗부분(인덱스가 house[0] 부분)은 가로로 움직일 수 밖에 없다..!
    }

    for (int i = 1; i < n; i++) {
        for (int j = 1; j < n; j++) {
            if (house[i][j] != 1) {
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
                if (house[i - 1][j] != 1 && house[i][j - 1] != 1) {
                     dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
                dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
            }
        }
    }

    cout << dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2] << '\n';

    return 0;
}