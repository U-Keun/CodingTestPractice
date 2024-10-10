#include <string>
#include <vector>

using namespace std;

#define ll long long
#define MOD 1000000007
#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(int n, int count) {
    vector<vector<ll>> dp(n, vector<ll>(n));
    REP(i, 0, n - 1) dp[0][i] = 1;
    
    REP(i, 1, n - 1) {
        REP(j, 0, n - i - 1) {
            dp[i][j] += dp[i - 1][j] * 2 * (i + j);
            dp[i][j] %= MOD;
        }
        
        REP(j, 0, n - i - 2) {
            dp[i][j + 1] += dp[i][j];
            dp[i][j + 1] %= MOD;
        }
    }
    
    return dp[n - count][count - 1];
}