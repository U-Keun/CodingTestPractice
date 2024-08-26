#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> money) {
    int l = money.size();
    vector<int> dp(l);
    dp[0] = money[0];
    dp[1] = money[0];
    for (int i = 2; i < l - 1; i++) {
        dp[i] = max(dp[i - 2] + money[i], dp[i - 1]);
    }
    int answer = max(dp[l - 2], dp[l - 3]);
    dp[0] = 0;
    dp[1] = money[1];
    for (int i = 2; i < l; i++) {
        dp[i] = max(dp[i - 2] + money[i], dp[i - 1]);
    }
    answer = max(answer, dp[l - 1]);
    
    return answer;
}