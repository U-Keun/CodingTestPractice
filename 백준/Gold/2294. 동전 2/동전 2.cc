#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int N, K; cin >> N >> K;
    vector<int> dp(K + 1, 10001);

    dp[0] = 0;

    REP(i, 0, N - 1) {
        int coin; cin >> coin;
        if(coin > K || dp[coin] == 1) continue;
        REP(j, coin, K) dp[j] = min(dp[j], dp[j - coin] + 1);
    }

    if(dp[K] == 10001) cout << -1 << '\n';
    else cout << dp[K] << '\n';
    return EXIT_SUCCESS;
}