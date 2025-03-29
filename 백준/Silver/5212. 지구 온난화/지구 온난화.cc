#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)
#define REP(i, a, b) for (int i = a; i <= b; i++)

using namespace std;

int dx[4] = { -1, 0, 0, 1 },
    dy[4] = { 0, -1, 1, 0 };

int main() {
    FAST_IO;

    int r, c; cin >> r >> c;
    vector<vector<char>> map(r, vector<char>(c));
    REP(i, 0, r - 1) REP(j, 0, c - 1) cin >> map[i][j];

    vector<vector<char>> new_map(r, vector<char>(c, '.'));
    int lt_r = r, lt_c = c, rb_r = -1, rb_c = -1; // left-top, right-bottom
    REP(i, 0, r - 1) {
        REP(j, 0, c - 1) {
            if (map[i][j] == '.') continue;

            int count = 0;
            REP(k, 0, 3) {
                int n_r = i + dx[k],
                    n_c = j + dy[k];
                
                if (n_r < 0 || n_r >= r
                    || n_c < 0 || n_c >= c) {
                    count++;
                    continue;
                }

                if (map[n_r][n_c] == '.') count++;
            }

            if (count < 3) {
                new_map[i][j] = 'X';
                lt_r = min(lt_r, i);
                lt_c = min(lt_c, j);
                rb_r = max(rb_r, i);
                rb_c = max(rb_c, j);
            }
        }
    }

    REP(i, lt_r, rb_r) {
        REP(j, lt_c, rb_c) {
            cout << new_map[i][j];
        }
        cout << '\n';
    }

    return EXIT_SUCCESS;
}