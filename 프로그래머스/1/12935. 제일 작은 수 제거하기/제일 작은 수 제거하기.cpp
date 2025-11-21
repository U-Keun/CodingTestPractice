#include <string>
#include <vector>
#include <climits>

using namespace std;

vector<int> solution(vector<int> arr) {
    int _min = INT_MAX;
    for (int num : arr) {
        if (num < _min) _min = num;
    }
    vector<int> answer;
    for (int num : arr) {
        if (num == _min) continue;
        answer.push_back(num);
    }
    if (answer.size() == 0) return vector<int>(1, -1);
    return answer;
}