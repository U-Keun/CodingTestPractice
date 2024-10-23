#include <string>
#include <vector>

using namespace std;

#define MAX 100000000000000

long long solution(int n, vector<int> times) {
    long long answer = 0;
    
    long long low = 0, high = MAX;
    while (low < high) {
        long long mid = (low + high) / 2;
        long long val = 0;
        for (int time : times) {
            val += mid / time;
        }
        
        if (val >= n) high = mid;
        else low = mid + 1;
    }
    
    return low;
}