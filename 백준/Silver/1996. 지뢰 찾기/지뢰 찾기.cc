#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int dr[8] = { -1, -1, -1, 0, 0, 1, 1, 1},
    dc[8] = { -1, 0, 1, -1, 1, -1, 0, 1};

int main() {
    FAST_IO

    int n;
    cin >> n;
    vector<vector<char>> map(n, vector<char>(n));
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            cin >> map[i][j];
        }
    }

    vector<vector<int>> answer(n, vector<int>(n));
    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            if (map[i][j] == '.') continue;
            REP(k, 0, 7) {
                int row = i + dr[k], col = j + dc[k];
                if (row < 0 || row >= n || col < 0 || col >= n) continue;
                answer[row][col] += (map[i][j] - '0');
            }
        }
    }

    REP(i, 0, n - 1) {
        REP(j, 0, n - 1) {
            if (map[i][j] == '.') {
                if (answer[i][j] > 9) cout << 'M';
                else cout << answer[i][j];
            } else cout << '*';
        }
        cout << '\n';
    }

    return 0;
}