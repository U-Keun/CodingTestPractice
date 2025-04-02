#include <iostream>
#include <vector>
#include <deque>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)
#define REP(i, a, b) for (int i = a; i < b; i++)

using namespace std;

int main() {
    FAST_IO;

    int n, m, r; cin >> n >> m >> r;
    vector<vector<int>> arr(n, vector<int>(m));
    REP(i, 0, n) REP(j, 0, m) cin >> arr[i][j];

    int q_cnt = min(n, m) / 2;
    vector<deque<int>> borders(q_cnt);
    int from_r = 0, from_c = 0, to_r = n - 1, to_c = m - 1;
    REP(i, 0, q_cnt) {
        // left to right
        for (int c = from_c; c <= to_c; c++) borders[i].push_back(arr[from_r][c]);

        // top to bottom
        for (int r = from_r + 1; r <= to_r; r++) borders[i].push_back(arr[r][to_c]);

        // right to left
        for (int c = to_c - 1; c >= from_c; c--) borders[i].push_back(arr[to_r][c]);

        // bottom to top
        for (int r = to_r - 1; r > from_r; r--) borders[i].push_back(arr[r][from_c]);
        
        from_r++; from_c++;
        to_r--; to_c--;
    }

    for (auto& border : borders) {
        int l = border.size(), r_idx = r % l;
        rotate(border.begin(), border.begin() + r_idx, border.end());
    }

    from_r = 0;
    from_c = 0;
    to_r = n - 1;
    to_c = m - 1;
    REP(i, 0, q_cnt) {
        // left to right
        for (int c = from_c; c <= to_c; c++) {
            arr[from_r][c] = borders[i].front();
            borders[i].pop_front();
        }

        // top to bottom
        for (int r = from_r + 1; r <= to_r; r++) {
            arr[r][to_c] = borders[i].front();
            borders[i].pop_front();
        }

        // right to left
        for (int c = to_c - 1; c >= from_c; c--) {
            arr[to_r][c] = borders[i].front();
            borders[i].pop_front();
        }

        // bottom to top
        for (int r = to_r - 1; r > from_r; r--) {
            arr[r][from_c] = borders[i].front();
            borders[i].pop_front();
        }
        
        from_r++; from_c++;
        to_r--; to_c--;
    }

    REP(i, 0, n) {
        REP(j, 0, m) {
            cout << arr[i][j] << ' ';
        }
        cout << '\n';
    }

    return EXIT_SUCCESS;
}