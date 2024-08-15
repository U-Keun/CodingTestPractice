#include <string>
#include <vector>
#include <deque>

using namespace std;

int solution(vector<int> a) {
    deque<int> deq;
    int answer = 0;
    for (int num : a) {
        if (!deq.empty() && deq.back() < num) {
            deq.push_back(num);
            continue;
        }
        
        while (!deq.empty() && deq.back() > num) {
            deq.pop_back();
        }
        
        if (deq.empty()) answer++;
        
        deq.push_back(num);
    }
    return answer + deq.size() - 1;
}