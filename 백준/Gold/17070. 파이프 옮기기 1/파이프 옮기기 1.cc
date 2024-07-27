#include <iostream>
#include <vector>
#define FAST_IO() \
    ios::sync_with_stdio(false); \
    cin.tie(NULL); \
    cout.tie(NULL);
#define ll long long

using namespace std;

bool canMoveDiagonally(const vector<vector<int> >& house, int i, int j) {
    return house[i - 1][j] != 1 && house[i][j - 1] != 1;
}

int main() {
    FAST_IO();

    int n;
    cin >> n;

    vector<vector<int> > house(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> house[i][j];
        }
    }

    vector<vector<vector<ll> > > dp(3, vector<vector<ll> >(n, vector<ll>(n, 0)));
    for (int i = 1; i < n; i++) {
        if (house[0][i] == 1) break;
        dp[0][0][i] = 1;
    }

    for (int i = 1; i < n; i++) {
        for (int j = 1; j < n; j++) {
            if (house[i][j] != 1) {
                dp[0][i][j] = dp[0][i][j - 1] + dp[1][i][j - 1];
                if (canMoveDiagonally(house, i, j)) {
                    dp[1][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
                }
                dp[2][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
            }
        }
    }

    cout << dp[0][n - 1][n - 1] + dp[1][n - 1][n - 1] + dp[2][n - 1][n - 1] << '\n';

    return 0;
}