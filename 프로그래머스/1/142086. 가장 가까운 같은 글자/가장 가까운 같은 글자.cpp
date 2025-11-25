#include <string>
#include <vector>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    vector<int> rec(26, -1);
    vector<char> v(s.begin(), s.end());
    int n = s.size();
    for (int i = 0; i < n; i++) {
        int prev = rec[v[i] - 'a'];
        if (prev < 0) answer.push_back(-1);
        else answer.push_back(i - prev);
        rec[v[i] - 'a'] = i;
    }
    return answer;
}