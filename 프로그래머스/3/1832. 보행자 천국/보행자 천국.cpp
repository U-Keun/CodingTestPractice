#include <vector>
#include <iostream>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int MOD = 20170805;

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int m, int n, vector<vector<int>> city_map) {
    vector<vector<int>> dp(m, vector<int>(n, 0));
    
    REP(i, 1, n - 1) {
        if (city_map[0][i] == 1) break;
        dp[0][i] = 1;
    }
    
    REP(i, 1, m - 1) {
        if (city_map[i][0] == 1) break;
        dp[i][0] = 1;
    }
    
    REP(i, 1, m - 1) {
        REP(j, 1, n - 1) {
            if (city_map[i][j] == 1) continue;
            
            // 위쪽에서 진입
            if (city_map[i - 1][j] == 0) {
                dp[i][j] += dp[i - 1][j];
            } else if (city_map[i - 1][j] == 2 && i != 1) {
                int idx = i - 2;
                while (idx >= 0 && city_map[idx][j] == 2) idx--;
                if (idx >= 0 && city_map[idx][j] != 2) dp[i][j] += dp[idx][j];
            }
            
            // 왼쪽에서 진입
            if (city_map[i][j - 1] == 0) {
                dp[i][j] += dp[i][j - 1];
            } else if (city_map[i][j - 1] == 2 && j != 1) {
                int idx = j - 2;
                while (idx >= 0 && city_map[i][idx] == 2) idx--;
                if (idx >= 0 && city_map[i][idx] != 2) dp[i][j] += dp[i][idx];
                
            }
            
            dp[i][j] %= MOD;
        }
    }
    
    return dp[m - 1][n - 1];
}