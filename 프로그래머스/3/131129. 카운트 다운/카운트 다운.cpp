#include <string>
#include <vector>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<int> solution(int target) {
    vector<pair<int, int>> dp(target + 1, { 100000, 0 });
    dp[0].first = 0;
    
    REP(i, 1, target) {
        if (i - 50 >= 0) {
            if (dp[i - 50].first + 1 < dp[i].first) {
                dp[i].first = dp[i - 50].first + 1;
                dp[i].second = dp[i - 50].second + 1;
            } else if (dp[i - 50].first + 1 == dp[i].first) {
                if (dp[i - 50].second + 1 > dp[i].second) {
                    dp[i].second = dp[i - 50].second + 1;
                }
            }
        }
        
        REP(j, 1, 20) {
            if (i - j >= 0) {
            	if (dp[i - j].first + 1 < dp[i].first) {
                	dp[i].first = dp[i - j].first + 1;
                	dp[i].second = dp[i - j].second + 1;
            	} else if (dp[i - j].first + 1 == dp[i].first) {
                	if (dp[i - j].second + 1 > dp[i].second) {
                    	dp[i].second = dp[i - j].second + 1;
                	}
            	}
        	}
            
            if (i - 2 * j >= 0) {
                if (dp[i - 2 * j].first + 1 < dp[i].first) {
                    dp[i].first = dp[i - 2 * j].first + 1;
                    dp[i].second = dp[i - 2 * j].second;
                } 
            }
            
            if (i - 3 * j >= 0) {
                if (dp[i - 3 * j].first + 1 < dp[i].first) {
                    dp[i].first = dp[i - 3 * j].first + 1;
                    dp[i].second = dp[i - 3 * j].second;
                } 
            }
        }
    }
    
    
    vector<int> answer;
    answer.push_back(dp[target].first);
    answer.push_back(dp[target].second);
    
    return answer;
}