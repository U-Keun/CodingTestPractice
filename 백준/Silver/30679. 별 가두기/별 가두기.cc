#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n, m;
    cin >> n >> m;

    vector<vector<int>> map(n, vector<int>(m));
    REP(i, 0, n - 1) {
        REP(j, 0, m - 1) {
            cin >> map[i][j];
        }
    }

    // 0 : E, 1 : S, 2 : W, 3 : N
    vector<int> answer;
    REP(i, 0, n - 1) {
        int cur_row = i, cur_col = 0, cur_dir = 0;
        bool isStarRanAway = true;
        vector<vector<vector<bool>>> record(n, vector<vector<bool>>(m, vector<bool>(4, false)));
        while (cur_row >= 0 && cur_row < n
            && cur_col >= 0 && cur_col < m) {
            if (record[cur_row][cur_col][cur_dir]) {
                isStarRanAway = false;
                break;
            }
            record[cur_row][cur_col][cur_dir] = true;
            switch (cur_dir) {
                case 0 :
                    cur_col += map[cur_row][cur_col];
                    break;
                case 1 :
                    cur_row += map[cur_row][cur_col];
                    break;
                case 2 :
                    cur_col -= map[cur_row][cur_col];
                    break;
                case 3 :
                    cur_row -= map[cur_row][cur_col];
                    break;
            }

            cur_dir++;
            cur_dir %= 4;
        }

        if (!isStarRanAway) {
            answer.push_back(i + 1);
        }
    }

    cout << answer.size() << '\n';
    for (int num : answer) cout << num << ' ';

    return 0;
}