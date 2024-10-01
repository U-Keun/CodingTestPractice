#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);
#define REP(i,a,b) for (int i = a; i <= b; i++)

using namespace std;

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<char>> board(n, vector<char>(m));
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) cin >> board[i][j];
    }

    int answer = 1;
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            char cur = board[i][j];
            REP(k, j + 1, m - 1) {
                if (board[i][k] == cur) {
                    int l = k - j;
                    if (i + l < n && board[i + l][j] == cur && board[i + l][k] == cur) {
                        answer = max(answer, (l + 1) * (l + 1));
                    }
                }
            }
        }
    }

    cout << answer;
    return 0;
}