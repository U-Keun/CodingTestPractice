#include <iostream>
#include <vector>

using namespace std;

int r, c, t;
vector<int> cleaner;
vector<int> dx = {1, -1, 0, 0};
vector<int> dy = {0, 0, -1, 1};

void diffuse(vector<vector<int>>& room) {
    vector<vector<int>> update(r, vector<int>(c, 0));
    for (int pos : cleaner) {
        update[pos][0] = -1;
    }

    for (int i = 0; i < r; ++i) {
        for (int j = 0; j < c; ++j) {
            if (room[i][j] <= 0) continue;
            if (room[i][j] <= 4) {
                update[i][j] += room[i][j];
                continue;
            }
            int diffusedDust = room[i][j] / 5;
            int row, col;
            for (int k = 0; k < 4; ++k) {
                row = i + dx[k];
                col = j + dy[k];
                if (row >= 0 && row < r
                    && col >= 0 && col < c
                    && room[row][col] != -1) {
                    update[row][col] += diffusedDust;
                    room[i][j] -= diffusedDust;
                }
            }
            update[i][j] += room[i][j];
        }
    }
    room = update;
}

void operateUpperPart(vector<vector<int>>& room) { // 방의 윗부분(반시계 방향) 공기 청정기 작동
    int idx = cleaner[0];
    for (int i = idx - 1; i > 0; i--) { // 왼쪽 모서리
        room[i][0] = room[i - 1][0];
    }
    for (int i = 0; i < c - 1; i++) { // 위쪽 모서리
        room[0][i] = room[0][i + 1];
    }
    for (int i = 0; i < idx; i++) { // 오른쪽 모서리
        room[i][c - 1] = room[i + 1][c - 1];
    }
    for (int i = c - 1; i > 0; i--) { // 아래쪽 모서리
        if (i == 1) {
            room[idx][i] = 0;
            continue;
        }
        room[idx][i] = room[idx][i - 1];
    }
}

void operateLowerPart(vector<vector<int>>& room) { // 방의 아랫부분(시계 방향) 공기 청정기 작동
    int idx = cleaner[1];
    for (int i = idx + 1; i < r - 1; i++) { // 왼쪽 모서리
        room[i][0] = room[i + 1][0];
    }
    for (int i = 0; i < c - 1; i++) { // 아래쪽 모서리
        room[r - 1][i] = room[r - 1][i + 1];
    }
    for (int i = r - 1; i > idx; i--) { // 오른쪽 모서리
        room[i][c - 1] = room[i - 1][c - 1];
    }
    for (int i = c - 1; i > 0; i--) { // 위쪽 모서리
        if (i == 1) {
            room[idx][i] = 0;
            continue;
        }
        room[idx][i] = room[idx][i - 1];
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> r >> c >> t;
    vector<vector<int>> room(r, vector<int>(c, 0));
    for (int i = 0; i < r; ++i) {
        for (int j = 0; j < c; ++j) {
            cin >> room[i][j];
            if (room[i][j] == -1) {
                cleaner.emplace_back(i);
            }
        }
    }

    while (t-- > 0) {
        diffuse(room);
        operateUpperPart(room);
        operateLowerPart(room);
    }


    int answer = 0;
    for (int i = 0; i < r; ++i) {
        for (int j = 0; j < c; ++j) {
            if (room[i][j] <= 0) continue;
            answer += room[i][j];
        }
    }
    cout << answer << '\n';
    return 0;
}