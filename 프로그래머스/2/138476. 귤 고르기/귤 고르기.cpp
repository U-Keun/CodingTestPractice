#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int k, vector<int> tangerine) {
    vector<int> record(10000001), tmp;
    for (int num : tangerine) { 
        record[num]++; 
        if (record[num] == 1) tmp.push_back(num);
    }
    sort(tmp.begin(), tmp.end(), [&](const int a, const int b) {
        return record[a] > record[b];
    });
    
    int answer = 0, i = 0;
    while (i < tmp.size() && k > 0) {
        answer++;
        k -= record[tmp[i++]];
    }
    
    return answer;
}