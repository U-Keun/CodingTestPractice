#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)
#define MOD 10000019
#define ll long long

vector<vector<ll>> pascal;

void setPascal(int row) {
    pascal.resize(row + 1, vector<ll>(row + 1, 0));
    REP(i, 0, row) {
        pascal[i][0] = pascal[i][i] = 1;
		REP(j, 1, i - 1) {
            pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % MOD;
        }
    }
}

int getPascal(int n, int k) {
    if (n < 0 || k < 0 || k > n) return 0;
    return pascal[n][k];
}

int solution(vector<vector<int>> a) {
    int row = a.size(), col = a[0].size();
    setPascal(max(row, col));
    
    vector<int> record(col, 0); // 각 열의 1의 개수 -> row - record[i] : 0의 개수
    REP(i, 0, col - 1) {
        REP(j, 0, row - 1) {
            if (a[j][i] == 1) record[i]++;
        }
    }
    
    vector<vector<long long>> dp(col, vector<long long>(row + 1));
    dp[0][row - record[0]] = pascal[row][record[0]] % MOD;
    REP(i, 1, col - 1) {
        if (record[i] == 0) {
            REP(j, 0, row) dp[i][j] = dp[i - 1][j];
            continue;
        }
        
        if (record[i] == row) {
            REP(j, 0, row) dp[i][j] = dp[i - 1][row - j];
            continue;
        }
        
    	REP(j, 0, row) {
            ll val = 0;
            REP(k, 0, record[i]) {
                int idx = j - record[i] + 2 * k;
                if (idx < 0 || idx > row) continue;
                val += dp[i - 1][idx] * getPascal(idx, k) % MOD * getPascal(row - idx, record[i] - k) % MOD;
            }
            
            dp[i][j] = val % MOD;
        }
    }
    
    return (int) dp[col - 1][row];
}