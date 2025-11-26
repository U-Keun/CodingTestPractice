#include <string>
#include <vector>
#include <map>

using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    map<string, int> m;
    int l = name.size();
    for (int i = 0; i < l; i++) {
        m[name[i]] = yearning[i];
    }
    vector<int> answer;
    for (auto& a : photo) {
        int val = 0;
        for (string p : a) {
            val += m[p];
        }
        answer.push_back(val);
    }
    return answer;
}