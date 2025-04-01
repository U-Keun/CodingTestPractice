#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)
#define SIZE 19
#define REP(i, a, b) for (int i = a; i < b; i++)

using namespace std;

int dx[4] = { 0, 1, 1, -1 },
    dy[4] = { 1, 0, 1, 1 },
    board[SIZE][SIZE];

bool is_valid_coord(int r, int c) {
    return r >= 0 && c >= 0 && r < SIZE && c < SIZE;
}

bool has_won(int r, int c, int color, int dir) {
    int cnt = 1,
        next_r = r + dx[dir], next_c = c + dy[dir];

    while (cnt < 5 && is_valid_coord(next_r, next_c)
           && board[next_r][next_c] == color) {
        cnt++;
        next_r += dx[dir];
        next_c += dy[dir];
    }

    if (cnt != 5) return false;

    int prev_r = r - dx[dir], prev_c = c - dy[dir];
    if (is_valid_coord(prev_r, prev_c) && board[prev_r][prev_c] == color) return false;

    if (is_valid_coord(next_r, next_c) && board[next_r][next_c] == color) return false;

    return true;
}

int main() {
    FAST_IO;

    REP(i, 0, SIZE) REP(j, 0, SIZE) cin >> board[i][j];

    REP(i, 0, SIZE) {
        REP(j, 0, SIZE) {
            if (board[i][j] == 0) continue;
            REP(dir, 0, 4) {
                if (has_won(i, j, board[i][j], dir)) {
                    cout << board[i][j] << '\n';
                    cout << i + 1 << ' ' << j + 1 << '\n';
                    return EXIT_SUCCESS;
                }
            }
        }
    }

    cout << 0 << '\n';

    return EXIT_SUCCESS;
}