#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

short w, h;
vector<vector<bool>> map;
int dx[8] = { -1, -1, -1, 0, 0, 1, 1, 1 }, dy[8] = { -1, 0, 1, -1, 1, -1, 0, 1 };

void bfs(int row, int col) {
    queue<pair<int, int>> q;
    q.push(make_pair(row, col));
    map[row][col] = 0;
    while (!q.empty()) {
        int l = q.size();
        for (int i = 0; i < l; i++) {
            pair<int, int> pos = q.front();
            q.pop();
            for (int j = 0; j < 8; j++) {
                int newRow = pos.first + dx[j],
                    newCol = pos.second + dy[j];
                if (newRow >= 0 && newRow < h && newCol >= 0 && newCol < w
                    && map[newRow][newCol]) {
                    q.push(make_pair(newRow, newCol));
                    map[newRow][newCol] = false;
                }
            }
        }
    }
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> w >> h;
    while (w != 0 && h != 0) {
        map.resize(h, vector<bool>(w));
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                short k;
                cin >> k;
                map[i][j] = (k == 1);
            }
        }

        short answer = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j]) {
                    answer++;
                    bfs(i, j);
                }
            }
        }
        cout << answer << '\n';
        cin >> w >> h;
    }

    return 0;
}