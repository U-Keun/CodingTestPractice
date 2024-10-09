#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <iostream>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int dx[4] = { -1, 0, 0, 1 }, // l, u, d, r
	dy[4] = { 0, -1, 1, 0 };

struct node {
public:
    int row, col;
    string word;
    
    bool operator < (const node& other) const {
        return word > other.word;
    }
};

string solution(int n, int m, int x, int y, int r, int c, int k) {
    if (abs(x - r) + abs(y - c) > k || (x + y + k) % 2 != (r + c) % 2) return "impossible";
    
    priority_queue<node> pq;
    pq.push({x, y, ""});
    while (!pq.empty()) {
        struct node tmp = pq.top();
        int l = tmp.word.size();
        pq.pop();
        
        if (l > k) continue;
        
        if (l == k) {
            if (tmp.row == r && tmp.col == c) return tmp.word;
            continue;
        }
        
        REP(i, 0, 3) {
            int nextRow = tmp.row + dy[i], nextCol = tmp.col + dx[i];
            if (nextRow >= 1 && nextRow <= n && nextCol >= 1 && nextCol <= m
               && (abs(nextRow - r) + abs(nextCol - c) < k - l)) {
                switch (i) {
                    case 0:
                        pq.push({nextRow, nextCol, tmp.word + 'l'});
                        break;
                    case 1:
                        pq.push({nextRow, nextCol, tmp.word + 'u'});
                        break;
                    case 2:
                        pq.push({nextRow, nextCol, tmp.word + 'd'});
                        break;
                    case 3:
                        pq.push({nextRow, nextCol, tmp.word + 'r'});
                        break;
                }
            }
        }
    }
    return "impossible";
}