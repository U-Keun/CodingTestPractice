#include <string>
#include <vector>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int row = board.size(), col = board[0].size();
    
    vector<vector<int>> record(row, vector<int>(col));
    for (const auto& s : skill) {
        int val = (s[0] == 1) ? -s[5] : s[5];
        record[s[1]][s[2]] += val;
        if (s[3] < row - 1 && s[4] < col - 1) record[s[3] + 1][s[4] + 1] += val;
        if (s[4] < col - 1) record[s[1]][s[4] + 1] -= val;
        if (s[3] < row - 1) record[s[3] + 1][s[2]] -= val;
    }
    
    REP(i, 0, row - 1) {
        REP(j, 0, col - 2) {
            record[i][j + 1] += record[i][j];
        }
    }
    REP(i, 0, row - 2) {
        REP(j, 0, col - 1) {
            record[i + 1][j] += record[i][j];
        }
    }
    
    int answer = 0;
    REP(i, 0, row - 1) {
        REP(j, 0, col - 1) {
            if (board[i][j] + record[i][j] > 0) answer++;
        }
    }
    
    return answer;
}