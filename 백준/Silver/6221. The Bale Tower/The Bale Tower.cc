#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    cin >> n;

    vector<pair<int, int>> bales(n);
    REP(i, 0, n - 1) cin >> bales[i].first >> bales[i].second;

    sort(bales.begin(), bales.end());

    int max_height = 1;
    vector<int> dp(n, 1);
    REP(i, 1, n - 1) {
        for (int j = 0; j < i; j++) {
            if (bales[j].first < bales[i].first && bales[j].second < bales[i].second) {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        max_height = max(max_height, dp[i]);
    }

    cout << max_height;

    return EXIT_SUCCESS;
}