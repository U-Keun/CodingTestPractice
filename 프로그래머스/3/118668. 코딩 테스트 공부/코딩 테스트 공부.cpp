#include <string>
#include <vector>
#include <queue>

using namespace std;

struct Node {
    int time, cur_alp, cur_cop;
    
    Node(int t, int a, int c) : time(t), cur_alp(a), cur_cop(c) {} 
    bool operator < (const Node& other) const {
        return time > other.time;
    }
};

int solution(int alp, int cop, vector<vector<int>> problems) {
    int target_alp = 0, target_cop = 0;
    for (auto& problem : problems) {
        target_alp = max(target_alp, problem[0]);
        target_cop = max(target_cop, problem[1]);
    }
    
    vector<vector<int>> record(151, vector<int>(151, -1));
    
    priority_queue<Node> pq;
    pq.push(Node(0, alp, cop));
    record[alp][cop] = 0;
    
    while (!pq.empty()) {
        Node tmp = pq.top();
        pq.pop();
        
        if (tmp.cur_alp >= target_alp && tmp.cur_cop >= target_cop) {
            return tmp.time;
        }
        
        if (record[min(150, tmp.cur_alp + 1)][tmp.cur_cop] == -1 ||
           record[min(150, tmp.cur_alp + 1)][tmp.cur_cop] > tmp.time + 1) {
            record[min(150, tmp.cur_alp + 1)][tmp.cur_cop] = tmp.time + 1;
            pq.push(Node(tmp.time + 1, min(150, tmp.cur_alp + 1), tmp.cur_cop));
        }
        
        if (record[tmp.cur_alp][min(150, tmp.cur_cop + 1)] == -1 ||
           record[tmp.cur_alp][min(150, tmp.cur_cop + 1)] > tmp.time + 1) {
            record[tmp.cur_alp][min(150, tmp.cur_cop + 1)] = tmp.time + 1;
            pq.push(Node(tmp.time + 1, tmp.cur_alp, min(150, tmp.cur_cop + 1)));
        }
        
        for (auto& problem : problems) {
            if (tmp.cur_alp < problem[0] || tmp.cur_cop < problem[1]) continue;
            
            int next_alp = min(150, tmp.cur_alp + problem[2]),
            	next_cop = min(150, tmp.cur_cop + problem[3]);
            if (record[next_alp][next_cop] == -1 ||
               record[next_alp][next_cop] > tmp.time + problem[4]) {
                record[next_alp][next_cop] = tmp.time + problem[4];
                pq.push(Node(tmp.time + problem[4], next_alp, next_cop));
            }
        }
    }
    
    return -1;
}