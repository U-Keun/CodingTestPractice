#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n, m;
vector<vector<int>> board;

int two_by_three(int i, int j) {
    int answer = INT_MIN, box = 0;
    REP(k, 0, 1) REP(l, 0, 2) box += board[i + k][j + l];

    int row_indices[8][2] = {
            {0, 0}, {0, 0}, {0, 0}, {0, 1},
            {1, 0}, {1, 1}, {1, 1}, {1, 1}
    }, col_indices[8][2] = {
            {0, 1}, {0, 2}, {1, 2}, {0, 2},
            {0, 2}, {0, 1}, {0, 2}, {1, 2}
    };

    REP(k, 0, 7) {
        int r1 = i + row_indices[k][0], c1 = j + col_indices[k][0],
            r2 = i + row_indices[k][1], c2 = j + col_indices[k][1];

        int current = box - (board[r1][c1] + board[r2][c2]);
        answer = max(answer, current);
    }

    return answer;
}

int three_by_two(int i, int j) {
    int answer = INT_MIN, box = 0;
    REP(k, 0, 2) REP(l, 0, 1) box += board[i + k][j + l];

    int row_indices[8][2] = {
            {0, 1}, {0, 2}, {1, 2}, {0, 2},
            {0, 2}, {0, 1}, {0, 2}, {1, 2}
    }, col_indices[8][2] = {
            {0, 0}, {0, 0}, {0, 0}, {0, 1},
            {1, 0}, {1, 1}, {1, 1}, {1, 1}
    };

    REP(k, 0, 7) {
        int r1 = i + row_indices[k][0],
            c1 = j + col_indices[k][0],
            r2 = i + row_indices[k][1],
            c2 = j + col_indices[k][1];

        int current = box - (board[r1][c1] + board[r2][c2]);
        answer = max(answer, current);
    }
    return answer;
}

int main() {
    FAST_IO

    cin >> n >> m;
    board.resize(n, vector<int>(m));
    REP(i, 0, n - 1) REP(j, 0, m - 1) cin >> board[i][j];

    int max_val = INT_MIN;
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            // 가로로 4칸
            if (j + 3 < m) {
                int sum4 = board[i][j] + board[i][j+1] + board[i][j+2] + board[i][j+3];
                max_val = max(max_val, sum4);
            }
            // 세로로 4칸
            if(i + 3 < n){
                int sum4 = board[i][j] + board[i+1][j] + board[i+2][j] + board[i+3][j];
                max_val = max(max_val, sum4);
            }
            // 2×2 정사각형
            if(i + 1 < n && j + 1 < m){
                int sumSquare = board[i][j] + board[i+1][j] + board[i][j+1] + board[i+1][j+1];
                max_val = max(max_val, sumSquare);
            }
            // 2×3 형태 (테트리스 모양)
            if(i + 1 < n && j + 2 < m){
                max_val = max(max_val, two_by_three(i, j));
            }
            // 3×2 형태 (테트리스 모양)
            if(i + 2 < n && j + 1 < m){
                max_val = max(max_val, three_by_two(i, j));
            }
        }
    }

    cout << max_val;

    return EXIT_SUCCESS;
}