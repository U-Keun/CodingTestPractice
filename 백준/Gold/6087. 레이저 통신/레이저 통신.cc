#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int w, h,
    dx[4] = { -1, 0, 0, 1 },
    dy[4] = { 0, -1, 1, 0 };

// from E : 0, S : 1, N : 2, W : 3

bool isValidPos(int row, int col) {
    return row >= 0 && row < h && col >= 0 && col < w;
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> w >> h;

    vector<vector<char>> map(h, vector<char>(w));
    vector<pair<int, int>> lightSource;
    REP(i, 0, h - 1) {
        REP(j, 0, w - 1) {
            cin >> map[i][j];
            if (map[i][j] == 'C') {
                lightSource.push_back({ i, j });
            }
        }
    }

    priority_queue<tuple<int, int, int, int>,
                    vector<tuple<int, int, int, int>>,
                    greater<>> pq;
    vector<vector<int>> record(h, vector<int>(w, 10000));
    record[lightSource[0].first][lightSource[0].second] = 0;
    REP(i, 0, 3) {
        int row = lightSource[0].first + dy[i],
            col = lightSource[0].second + dx[i];

        if (isValidPos(row, col) && map[row][col] != '*') {
            if (map[row][col] == 'C') {
                cout << 0;
                return 0;
            }

            pq.emplace(0, row, col, i);
            record[row][col] = 0;
        }
    }

    while (!pq.empty()) {
        int m = get<0>(pq.top()),
            r = get<1>(pq.top()),
            c = get<2>(pq.top()),
            from = get<3>(pq.top());
        pq.pop();

        if (r == lightSource[1].first && c == lightSource[1].second) {
            cout << m;
            return 0;
        }

        REP(i, 0, 3) {
            int row = r + dy[i],
                col = c + dx[i];

            if (isValidPos(row, col) && map[row][col] != '*') {
                int val = m + (i != from);
                if (val < record[row][col] || (val == record[row][col] && i == from)) {
                    record[row][col] = val;
                    pq.emplace(val, row, col, i);
                }
            }
        }
    }

    return 0;
}