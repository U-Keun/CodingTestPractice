#include <string>
#include <vector>
#include <iostream>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)
#define ll long long
#define MOD 1000000007

int dx[4] = { -1, 0, 0, 1 },
	dy[4] = { 0, -1, 1, 0 };

int row, col;

bool isValidPos(int r, int c) {
    return r >= 0 && r < row && c >= 0 && c < col;
}

vector<vector<ll>> matrix_product(vector<vector<ll>> A, vector<vector<ll>> B) {
	int n = A.size();
    vector<vector<ll>> answer(n, vector<ll>(n, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            ll value = 0;
            for (int k = 0; k < n; k++) {
                value += (A[i][k] * B[k][j]) % MOD;
            }
            answer[i][j] = value % MOD;
        }
    }
    return answer;
}

vector<vector<ll>> matrix_power(vector<vector<ll>> matrix, long long k) {
	int n = matrix.size();
    vector<vector<ll>> res(n, vector<ll>(n));
    for (int i = 0; i < n; i++) {
        res[i][i] = 1;
    }
    while (k > 0) {
        if (k % 2 == 1) {
            res = matrix_product(res, matrix);
        }
        matrix = matrix_product(matrix, matrix);
        k /= 2;
    }
    return res;
}

int solution(vector<vector<int>> grid, vector<int> d, int k) {
    row = grid.size();
    col = grid[0].size();
    int l = d.size();
    vector<vector<vector<ll>>> dp(l + 1, vector<vector<ll>>(row * col, vector<ll>(row * col)));
    REP(i, 0, row * col - 1) dp[0][i][i] = 1;
    
    REP(i, 1, l) {
        REP(j, 0, row * col - 1) {
            int curRow = j / col, curCol = j % col;
            REP(k, 0, 3) {
                int r = curRow + dy[k], c = curCol + dx[k];
                if (isValidPos(r, c) && grid[r][c] == grid[curRow][curCol] + d[i - 1]) {
                    REP(m, 0, row * col - 1) {
                        dp[i][m][r * col + c] += dp[i - 1][m][j] % MOD;
                        dp[i][m][r * col + c] %= MOD;
                    }
                }
            }
        }
    }
    
    vector<vector<ll>> result = matrix_power(dp[l], k);
    ll answer = 0;
    REP(i, 0, row * col - 1) {
        REP(j, 0, row * col - 1) {
            answer += result[i][j];
            answer %= MOD;
        }
    }
    return (int) answer;
}