#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int board[9][9], check_board[3][9];

struct Blank {
    int row_idx, col_idx;
    Blank(int r, int c) : row_idx(r), col_idx(c) {}
};

vector<Blank> blanks;
int m;

bool recurrence(int idx) {
    if (idx == m) return true;

    int row = blanks[idx].row_idx,
        col = blanks[idx].col_idx,
        box_index = (row / 3) * 3 + (col / 3);

    REP(i, 1, 9) {
        int mask =1 << i;
        if ((check_board[0][row] & mask) != 0) continue;
        if ((check_board[1][col] & mask) != 0) continue;
        if ((check_board[2][box_index] & mask) != 0) continue;

        check_board[0][row] |= mask;
        check_board[1][col] |= mask;
        check_board[2][box_index] |= mask;
        board[row][col] = i;

        if (recurrence(idx + 1)) return true;

        check_board[0][row] &= ~mask;
        check_board[1][col] &= ~mask;
        check_board[2][box_index] &= ~mask;
        board[row][col] = 0;
    }
    return false;
}

int main() {
    FAST_IO

    REP(i, 0, 8) {
        REP(j, 0, 8) {
            int val; cin >> val;
            board[i][j] = val;
            if (val == 0) {
                blanks.emplace_back(i, j);
            } else {
                int mask = 1 << val;
                check_board[0][i] |= mask;
                check_board[1][j] |= mask;
                check_board[2][(i / 3) * 3 + (j / 3)] |= mask;
            }
        }
    }

    m = blanks.size();

    recurrence(0);

    REP(i, 0, 8) {
        REP(j, 0, 8) {
            cout << board[i][j] << ' ';
        }
        cout << '\n';
    }

    return EXIT_SUCCESS;
}