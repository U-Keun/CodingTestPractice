#include <string>
#include <vector>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int row, col, answer;
vector<vector<int>> current, target;

void checkConvertable(int count) {
    bool convertable = true;
    int c = 0;
    REP(i, 0, col - 1) {
        bool convertableCol = true;
        int val = 0;
        REP(j, 0, row - 1) {
            if (current[j][i] == target[j][i]) val++;
        }
        
        if (val == 0) c++;
        else if (val < row) convertableCol = false;
        
        if (!convertableCol) {
            convertable = false;
            break;
        }
    }
    
    if (convertable) answer = min(answer, count + c);   
}

void rowSelection(int r, int count) {
    if (r == row) {
        checkConvertable(count);
        return;
    }
    
    rowSelection(r + 1, count);
    REP(i, 0, col - 1) current[r][i] = !current[r][i];
    rowSelection(r + 1, count + 1);
    REP(i, 0, col - 1) current[r][i] = !current[r][i];
}

int solution(vector<vector<int>> beginning, vector<vector<int>> t) {
    row = beginning.size();
    col = beginning[0].size();
    current = beginning;
    target = t;
    answer = 11;
    
    rowSelection(0, 0);
    if (answer == 11) return -1;
    return answer;
}