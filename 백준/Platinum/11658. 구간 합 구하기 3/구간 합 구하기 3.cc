#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int n;
vector<vector<long long>> seg_tree;
vector<vector<int>> input;

void generate_col(int cur_row, int row_from, int row_to,
              int cur_col, int col_from, int col_to) {
    if (col_from == col_to) {
        if (row_from == row_to) {
            seg_tree[cur_row][cur_col] = input[row_from][col_from];
            return;
        }
        seg_tree[cur_row][cur_col] = seg_tree[cur_row * 2][cur_col] + seg_tree[cur_row * 2 + 1][cur_col];
        return;
    }

    int mid_col = (col_from + col_to) / 2;
    generate_col(cur_row, row_from, row_to, cur_col * 2, col_from, mid_col);
    generate_col(cur_row, row_from, row_to, cur_col * 2 + 1, mid_col + 1, col_to);
    seg_tree[cur_row][cur_col] = seg_tree[cur_row][cur_col * 2] + seg_tree[cur_row][cur_col * 2 + 1];
}

void generate_row(int cur_row, int row_from, int row_to) {
    if (row_from == row_to) {
        generate_col(cur_row, row_from, row_to, 1, 0, n - 1);
        return;
    }
    int mid_row = (row_from + row_to) / 2;
    generate_row(cur_row * 2, row_from, mid_row);
    generate_row(cur_row * 2 + 1, mid_row + 1, row_to);
    generate_col(cur_row, row_from, row_to, 1, 0, n - 1);
}

void update_col(int cur_row, int row_from, int row_to,
                int cur_col, int col_from, int col_to,
                int target_col, int val) {
    if (target_col < col_from || target_col > col_to) return;
    if (col_from == col_to) {
        if (row_from == row_to) {
            seg_tree[cur_row][cur_col] = val;
            return;
        }
        seg_tree[cur_row][cur_col] = seg_tree[cur_row * 2][cur_col] + seg_tree[cur_row * 2 + 1][cur_col];
        return;
    }

    int mid_col = (col_from + col_to) / 2;
    update_col(cur_row, row_from, row_to, cur_col * 2, col_from, mid_col, target_col, val);
    update_col(cur_row, row_from, row_to, cur_col * 2 + 1, mid_col + 1, col_to, target_col, val);
    seg_tree[cur_row][cur_col] = seg_tree[cur_row][cur_col * 2] + seg_tree[cur_row][cur_col * 2 + 1];
}

void update_row(int cur_row, int row_from, int row_to,
                int target_row, int target_col, int val) {
    if (target_row < row_from || target_row > row_to) return;
    if (row_from == row_to) {
        update_col(cur_row, row_from, row_to, 1, 0, n - 1, target_col, val);
        return;
    }
    int mid_row = (row_from + row_to) / 2;
    update_row(cur_row * 2, row_from, mid_row, target_row, target_col, val);
    update_row(cur_row * 2 + 1, mid_row + 1, row_to, target_row, target_col, val);
    update_col(cur_row, row_from, row_to, 1, 0, n - 1, target_col, 0);
}

long long query_col(int cur_row, int row_from, int row_to,
                    int cur_col, int col_from, int col_to,
                    int y1, int y2) {
    if (y2 < col_from || y1 > col_to) return 0;
    if (y1 <= col_from && y2 >= col_to) return seg_tree[cur_row][cur_col];
    int mid_col = (col_from + col_to) / 2;
    return query_col(cur_row, row_from, row_to, cur_col * 2, col_from, mid_col, y1, y2) +
        query_col(cur_row, row_from, row_to, cur_col * 2 + 1, mid_col + 1, col_to, y1, y2);
}

long long query_row(int cur_row, int row_from, int row_to,
                    int x1, int x2, int y1, int y2) {
    if (x2 < row_from || x1 > row_to) return 0;
    if (x1 <= row_from && x2 >= row_to)
        return query_col(cur_row, row_from, row_to, 1, 0, n - 1, y1, y2);

    int mid_row = (row_from + row_to) / 2;
    return query_row(cur_row * 2, row_from, mid_row, x1, x2, y1, y2) +
        query_row(cur_row * 2 + 1, mid_row + 1, row_to, x1, x2, y1, y2);
}

int main() {
    FAST_IO

    int m; cin >> n >> m;
    input.resize(n, vector<int>(n));
    REP(i, 0, n - 1) REP(j, 0, n - 1) cin >> input[i][j];
    seg_tree.resize(4 * n, vector<long long>(4 * n));
    generate_row(1, 0, n - 1);

    int w;
    while (m-- > 0) {
        cin >> w;
        if (w) {
            int x1, y1, x2, y2; cin >> x1 >> y1 >> x2 >> y2;
            long long ans = query_row(1, 0, n - 1, x1 - 1, x2 - 1, y1 - 1, y2 - 1);
            cout << ans << '\n';
        } else {
            int x, y, c; cin >> x >> y >> c;
            update_row(1, 0, n - 1, x - 1, y  - 1, c);
        }
    }

    return EXIT_SUCCESS;
}