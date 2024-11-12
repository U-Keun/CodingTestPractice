#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define ll long long
#define REP(i,a,b) for (int i = a; i <= b; i++)

long long solution(vector<int> sequence) {
    int n = sequence.size();
    vector<vector<ll>> dp(2, vector<ll>(n));
    REP(i, 0, n - 1) {
        dp[1][i] = sequence[i];
        dp[0][i] = sequence[i] * (-1);
    }
    
    ll answer = max(dp[0][0], dp[1][0]);
    REP(i, 1, n - 1) {
        dp[0][i] = max(dp[0][i], dp[1][i - 1] - sequence[i]);
        dp[1][i] = max(dp[1][i], dp[0][i - 1] + sequence[i]);
        answer = max(answer, max(dp[0][i], dp[1][i]));
    }
    
    return answer;
}