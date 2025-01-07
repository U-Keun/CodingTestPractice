#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int R, C;
vector<string> board;
int max_val = 1;

int dx[4] = {0, 0, 1, -1},
    dy[4] = {1, -1, 0, 0};

void dfs(int i, int j, int mask, int count_val){
    max_val = max(max_val, count_val);

    REP(k, 0, 3) {
        int ni = i + dy[k];
        int nj = j + dx[k];

        if(ni >= 0 && ni < R && nj >= 0 && nj < C){
            char next_char = board[ni][nj];
            int char_bit = 1 << (next_char - 'A');

            if( (mask & char_bit) == 0 ){
                dfs(ni, nj, mask | char_bit, count_val + 1);
            }
        }
    }
}

int main() {
    FAST_IO

    cin >> R >> C;
    board.assign(R, "");
    REP(i, 0, R - 1) cin >> board[i];

    int initial_mask = 1 << (board[0][0] - 'A');
    dfs(0, 0, initial_mask, 1);

    cout << max_val;

    return EXIT_SUCCESS;
}