#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n;
long long cases_count = 0;

vector<bool> col_used,
    right_down_diag,
    left_up_diag;

void backtracking(int row) {
    if (row == n) {
        cases_count++;
        return;
    }

    REP(col, 0, n - 1) {
        if (!col_used[col] && !right_down_diag[row + col] && !left_up_diag[row - col + (n - 1)]) {
            col_used[col] = true;
            right_down_diag[row + col] = true;
            left_up_diag[row - col + (n - 1)] = true;
            backtracking(row + 1);
            col_used[col] = false;
            right_down_diag[row + col] = false;
            left_up_diag[row - col + (n - 1)] = false;
        }
    }
}

int main() {
    FAST_IO

    cin >> n;
    col_used.resize(n, false);
    right_down_diag.resize(2 * n - 1, false);
    left_up_diag.resize(2 * n - 1, false);
    backtracking(0);

    cout << cases_count;

    return EXIT_SUCCESS;
}