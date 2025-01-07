#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;


vector<vector<char>> board;
vector<vector<bool>> visited;
int N;

int dx[4] = {0, 0, 1, -1},
    dy[4] = {1, -1, 0, 0};

void find_region(int row, int col) {
    char color = board[row][col]; // 현재 색상 저장

    for(int i = 0; i < 4; i++) {
        int newRow = row + dx[i], newCol = col + dy[i];

        if(newRow >= 0 && newRow < N && newCol >= 0 && newCol < N
           && !visited[newRow][newCol]
           && board[newRow][newCol] == color) {
            visited[newRow][newCol] = true;
            find_region(newRow, newCol);
        }
    }

    if(board[row][col] == 'G') {
        board[row][col] = 'R';
    }
}

int getCount() {
    for(int i = 0; i < N; i++) {
        fill(visited[i].begin(), visited[i].end(), false);
    }

    int count = 0;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            if(!visited[i][j]) {
                visited[i][j] = true;
                count++;
                find_region(i, j);
            }
        }
    }

    return count;
}

int main() {
    FAST_IO

    cin >> N;
    board.assign(N, vector<char>(N));

    for(int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for(int j = 0; j < N; j++) {
            board[i][j] = s[j];
        }
    }

    visited.assign(N, vector<bool>(N, false));

    int count1 = getCount(), count2 = getCount();
    cout << count1 << " " << count2;

    return EXIT_SUCCESS;
}