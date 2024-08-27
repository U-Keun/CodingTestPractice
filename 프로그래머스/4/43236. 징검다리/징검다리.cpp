#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(int distance, vector<int> rocks, int n) {
    if (n == rocks.size()) return distance;
    
    sort(rocks.begin(), rocks.end());
    rocks.push_back(distance);
    
    int left = 0, right = distance, answer = distance;
    while (left < right) {
        int mid = (left + right) / 2;
        
        int deleted = 0, prev = 0;
        for (int rock : rocks) {
            if (rock - prev < mid) {
                deleted++;
            } else {
                prev = rock;
            }
            
            if (deleted > n) break;
        }
        
        if (deleted > n) {
            right = mid;
        } else {
            answer = mid;
            left = mid + 1;
        }
    }
    
    return answer;
}