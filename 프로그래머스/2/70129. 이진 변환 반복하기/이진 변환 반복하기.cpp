#include <string>
#include <vector>

using namespace std;

vector<int> solution(string s) {
    string tmp = "";
    int cnt, zeros = 0, convert = 0;
    while (s != "1") {
        cnt = 0;
        for (char c : s) {
            if (c == '1') cnt++;
            else zeros++;
        }
        while (cnt) {
            tmp = to_string(cnt % 2) + tmp;
            cnt /= 2;
        }
        s = tmp;
        tmp = "";
        convert++;
    }
    vector<int> answer = { convert, zeros };
    return answer;
}