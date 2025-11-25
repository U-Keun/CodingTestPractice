#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<bool> freq(201, false);
    int n = numbers.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            freq[numbers[i] + numbers[j]] = true;
        }
	}
    
    vector<int> answer;
    for (int i = 0; i < 201; i++) {
        if (freq[i]) answer.push_back(i);
    }
    
    return answer;
}