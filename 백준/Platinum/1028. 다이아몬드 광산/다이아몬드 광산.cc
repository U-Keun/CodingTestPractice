#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int r, c; cin >> r >> c;
    vector<vector<int>> board(r, vector<int>(c));
    char num;
    REP(i, 0, r - 1) {
        REP(j, 0, c - 1) {
            cin >> num;
            board[i][j] = (num == '1') ? 1 : 0;
        }
    }

    vector<vector<int>> up_left(r, vector<int>(c)),
        up_right(r, vector<int>(c)),
        down_left(r, vector<int>(c)),
        down_right(r, vector<int>(c));

    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            if(board[i][j] == 1){
                if(i == 0 || j == 0) up_left[i][j] = 1;
                else up_left[i][j] = up_left[i - 1][j - 1] + 1;

                if(i == 0 || j == c - 1) up_right[i][j] = 1;
                else up_right[i][j] = up_right[i - 1][j + 1] + 1;
            }
        }
    }

    for(int i = r - 1; i >= 0; i--){
        for(int j = 0; j < c; j++){
            if(board[i][j] == 1){
                if(i == r - 1 || j == 0) down_left[i][j] = 1;
                else down_left[i][j] = down_left[i + 1][j - 1] + 1;

                if(i == r - 1 || j == c - 1) down_right[i][j] = 1;
                else down_right[i][j] = down_right[i + 1][j + 1] + 1;
            }
        }
    }

    int answer = 0;
    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            if (board[i][j] == 0) continue;
            int max_k = min(down_left[i][j], down_right[i][j]);
            for (int k = answer + 1; k <= max_k; k++) {
                int new_i = i + 2 * (k - 1);
                if (new_i >= r) break;
                if(board[new_i][j] && up_left[new_i][j] >= k && up_right[new_i][j] >= k) {
                    answer = max(answer, k);
                }
            }

            max_k = min(down_right[i][j], up_right[i][j]);
            for (int k = answer + 1; k <= max_k; k++) {
                int new_j = j + 2 * (k - 1);
                if(new_j >= c) break;
                if(board[i][new_j] && up_left[i][new_j] >= k && down_left[i][new_j] >= k) {
                    answer = max(answer, k);
                }
            }
        }
    }

    cout << answer;

    return EXIT_SUCCESS;
}