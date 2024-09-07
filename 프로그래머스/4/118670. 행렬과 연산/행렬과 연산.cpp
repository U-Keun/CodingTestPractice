#include <string>
#include <vector>
#include <deque>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

vector<vector<int>> solution(vector<vector<int>> rc, vector<string> operations) { 
    int rowIdx = rc.size() - 1, colIdx = rc[0].size() - 1;
    vector<deque<int>> center(rowIdx + 1);
    bool isCenterNull = (colIdx == 1);
    deque<int> leftEdge, rightEdge;
    
    // 연산 준비
    REP(i, 0, rowIdx) { // 왼쪽 변, 오른쪽 변 deque 준비(인덱스가 작은 부분이 front)
        leftEdge.push_back(rc[i][0]);
        rightEdge.push_back(rc[i][colIdx]);
    }
    
    REP(i, 0, rowIdx) { // 양쪽 변을 제외한 부분 저장(인덱스가 작은 부분이 front)
        REP(j, 1, colIdx - 1) {
            center[i].push_back(rc[i][j]);
        }
    }
    
    int offset = 0; // center 변수의 실제 시작 지점
    
    for (string operation : operations) { // 연산 시작
        if (operation == "ShiftRow") {
            if (offset == 0) offset = rowIdx;
            else offset--;
            
            leftEdge.push_front(leftEdge.back());
            leftEdge.pop_back();
            rightEdge.push_front(rightEdge.back());
            rightEdge.pop_back();
        } else {
            if (isCenterNull) {
                rightEdge.push_front(leftEdge.front());
                leftEdge.pop_front();
                leftEdge.push_back(rightEdge.back());
                rightEdge.pop_back();
                continue;
            }
            
            // 왼쪽 위 모서리
            center[offset].push_front(leftEdge.front());
            leftEdge.pop_front();
            // 오른쪽 위 모서리
            rightEdge.push_front(center[offset].back());
            center[offset].pop_back();
            
            int bot = (offset + rowIdx) % (rowIdx + 1);
            // 오른쪽 아래 모서리
            center[bot].push_back(rightEdge.back());
            rightEdge.pop_back();
            // 왼쪽 아래 모서리
            leftEdge.push_back(center[bot].front());
            center[bot].pop_front();
        }
    }
    
    // rc에 값 다시 저장하기
    REP(i, 0, rowIdx) {
        rc[i][0] = leftEdge.front();
        leftEdge.pop_front();
        rc[i][colIdx] = rightEdge.front();
        rightEdge.pop_front();
        
        int actualRowIdx = (i + offset) % (rowIdx + 1);
        REP(j, 1, colIdx - 1) {
            rc[i][j] = center[actualRowIdx][j - 1];
        }
    }
    
    return rc;
}