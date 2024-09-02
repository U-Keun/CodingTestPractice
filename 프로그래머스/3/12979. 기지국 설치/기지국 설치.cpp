#include <iostream>
#include <vector>
using namespace std;

int solution(int n, vector<int> stations, int w) {
    int answer = 0;
    
    int current = 1;
    for (int station : stations) {
        if (current < station - w) {
            int val = station - w - current;
            answer += (val - 1) / (2 * w + 1) + 1;
        }
        current = station + w + 1;
        
    }
    
    if (current <= n) 
    	answer += (n - current - 1) / (2 * w + 1) + 1;

    return answer;
}