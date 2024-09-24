#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define ll long long
#define REP(i,a,b) for (int i = a; i <= b; i++)
#define MOD 1000000007

int solution(int n) {
    vector<ll> dp(max(6, n + 1));
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 3;
    dp[3] = 10;
    dp[4] = 23;
    dp[5] = 62;
    REP(i, 6, n) {
        dp[i] = (dp[i - 1] + (2 * dp[i - 2]) % MOD 
            	+ (6 * dp[i - 3]) % MOD 
            	+ dp[i - 4]) % MOD;
        dp[i] -= dp[i - 6];
        if (dp[i] < 0) dp[i] += MOD;
    }
    
    
    return dp[n];
}