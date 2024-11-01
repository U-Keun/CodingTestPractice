#include <string>
#include <vector>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)
#define MOD 1000000007

int solution(int n, vector<int> money) {
    vector<int> dp(n + 1);
    dp[0] = 1;
    for (int exchange : money) {
        REP(i, exchange, n) {
            dp[i] += dp[i - exchange];
            dp[i] %= MOD;
        }
    }
    
    return dp[n];
}