#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(vector<int> food_times, long long k) {
    int answer = 0;
    
    int n = food_times.size(); // 남아 있는 음식 종류
    vector<pair<int, int>> pairs;
    REP(i, 0, n - 1) {
        pairs.emplace_back(food_times[i], i + 1);
    }
    sort(pairs.begin(), pairs.end());
    
    long long minLine = 0;
    for (auto it = pairs.begin(); it != pairs.end(); ++it) {
        if (minLine == it->first) {
            n--;
            continue;
        }
        
        if (k < n * (it->first - minLine)) {
            k %= n;
            sort(it, pairs.end(), [](pair<int, int> a, pair<int, int> b) {
                return a.second < b.second;
            });
            
            return (it + k)->second;
        } else {
            k -= n * (it->first - minLine);
            minLine = it->first;
            n--;
        }
        
    }
    
    return -1;
}