#include <iostream>
#include <vector>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP_INC(i,a,b) for (int i = a; i <= b; i++)
#define REP_DEC(i,a,b) for (int i = a; i >= b; i--)
#define BLANK 6
#define BLACK -1

int n, m;
vector<vector<short>> board;
int dx[4] = { -1, 0, 0, 1 },
    dy[4] = {0, -1, 1, 0 };

bool isValidPosition(int row, int col) {
    return row >= 0 && row < n && col >= 0 && col < n;
}

// board 반시계 방향 회전
void rotate() {
    vector<vector<short>> rotated(n, vector<short>(n));
    REP_INC(i, 0, n - 1) {
        REP_INC(j, 0, n - 1) {
            rotated[i][j] = board[j][n - i - 1];
        }
    }
    board = rotated;
}

// 중력 작용
void gravityOn() {
    REP_INC(j, 0, n - 1) {
        REP_DEC(i, n - 2, 0) {
            if (board[i][j] == BLANK || board[i][j] == BLACK) continue;
            int current = i;
            while (current < n - 1 && board[current + 1][j] == BLANK) current++;
            if (current != i && current != n) {
                board[current][j] = board[i][j];
                board[i][j] = BLANK;
            }
        }
    }
}

// 가장 큰 블록 그룹 찾기
int calcBlocks() {
    int blocks = 0;
    vector<vector<bool>> visited(n, vector<bool>(n, false));

    vector<pair<int, int>> candidates;
    int rainbows = 0, row, col;
    REP_INC(i, 0, n - 1) {
        REP_INC(j, 0, n - 1) {
            if (visited[i][j]) continue;
            if (board[i][j] >= 1 && board[i][j] <= m) {
                queue<pair<int, int>> positions;
                vector<pair<int, int>> tmp;
                int color = board[i][j];
                positions.push(make_pair(i, j));
                tmp.push_back(make_pair(i, j));
                visited[i][j] = true;
                vector<pair<int, int>> rainbow;
                int val = 1;

                while (!positions.empty()) {
                    pair<int, int> position = positions.front();
                    positions.pop();
                    int rowIdx, colIdx;
                    REP_INC(k, 0, 3) {
                        rowIdx = position.first + dy[k];
                        colIdx = position.second + dx[k];
                        if (isValidPosition(rowIdx, colIdx)
                            && !visited[rowIdx][colIdx]
                            && (board[rowIdx][colIdx] == color || board[rowIdx][colIdx] == 0)) {
                            pair<int, int> validPos = make_pair(rowIdx, colIdx);
                            positions.push(validPos);
                            tmp.push_back(validPos);
                            visited[rowIdx][colIdx] = true;
                            val++;

                            if (board[rowIdx][colIdx] == 0) rainbow.push_back(validPos);
                        }
                    }
                }

                if (val > 1) {
                    if (val > blocks) {
                        row = i;
                        col = j;
                        candidates = tmp;
                        blocks = val;
                        rainbows = rainbow.size();
                    } else if (val == blocks) {
                        if (rainbows < rainbow.size()) {
                            row = i;
                            col = j;
                            candidates = tmp;
                            rainbows = rainbow.size();
                        } else if (rainbows == rainbow.size()) {
                            if (row < i) {
                                row = i;
                                col = j;
                                candidates = tmp;
                            } else if (row == i) {
                                if (col < j) {
                                    col = j;
                                    candidates = tmp;
                                }
                            }
                        }
                    }
                }

                for (pair<int, int> pos : rainbow) {
                    visited[pos.first][pos.second] = false;
                }
            }
        }
    }

    for (pair<int, int> pos : candidates) {
        board[pos.first][pos.second] = BLANK;
    }

    return blocks;
}

int main() {
// int algorithm() {
    FAST_IO

    cin >> n >> m;
    board.resize(n, vector<short>(n));
    REP_INC(i, 0, n - 1) {
        REP_INC(j, 0, n - 1) {
            cin >> board[i][j];
        }
    }

    int answer = 0, maxBlock = calcBlocks();
    while(maxBlock != 0) {
        answer += maxBlock * maxBlock;
        gravityOn();
        rotate();
        gravityOn();

        maxBlock = calcBlocks();
    }

    cout << answer;

    return 0;
}