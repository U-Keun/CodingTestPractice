#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(vector<int> sticker) {
    int n = sticker.size();
    vector<vector<int>> dp(2, vector<int>(n));
    dp[0][0] = sticker[0];
    if (n > 1) {
        dp[0][1] = max(dp[0][0], sticker[1]);
    	dp[1][1] = sticker[1];    
    }
    
    REP(i, 2, n - 1) {
        if (i < n - 1) dp[0][i] = max(dp[0][i - 1], dp[0][i - 2] + sticker[i]);
        else dp[0][i] = dp[0][i - 1];
        dp[1][i] = max(dp[1][i - 1], dp[1][i - 2] + sticker[i]);
    }
    
    return max(dp[0][n - 1], dp[1][n - 1]);
}