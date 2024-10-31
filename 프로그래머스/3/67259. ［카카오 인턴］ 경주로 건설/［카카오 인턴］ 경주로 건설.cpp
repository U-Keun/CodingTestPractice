#include <string>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int dr[4] = { 0, 1, 0, -1 }, dc[4] = { 1, 0, -1, 0 };

int solution(vector<vector<int>> board) {
    int n = board.size();
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(4, n * n * 600)));
    REP(i, 0, 3) dp[0][0][i] = 0; // 0 : from W, 1 : from N, 2 : from E, 3 : from S
    queue<tuple<int, int, int>> q;
    if (!board[0][1]) {
        q.push({ 0, 1, 0 });
        dp[0][1][0] = 100;
    }
    if (!board[1][0]) {
        q.push({ 1, 0, 1 });
        dp[1][0][1] = 100;
    }
    
    while (!q.empty()) {
        int row = get<0>(q.front()), col = get<1>(q.front()), dir = get<2>(q.front());
        q.pop();
        
        REP(i, 0, 3) {
            int nr = row + dr[i], nc = col + dc[i];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc]) continue;
            int cost = (i == dir) ? 100 : 600;
            if (dp[row][col][dir] + cost < dp[nr][nc][i]) {
                dp[nr][nc][i] = dp[row][col][dir] + cost;
                q.push({ nr, nc, i });
            }
        }
    }
    
    int answer = dp[n - 1][n - 1][0];
    REP(i, 1, 3) answer = min(answer, dp[n - 1][n - 1][i]);
    return answer;
}