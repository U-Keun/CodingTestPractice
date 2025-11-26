#include <string>
#include <vector>

using namespace std;

const string words[10] = {
    "zero", "one", "two", "three", "four",
    "five", "six", "seven", "eight", "nine"
};

int solution(string s) {
    int l = s.size(), idx = 0, answer = 0;
    while (idx < l) {
        if ('0' <= s[idx] && s[idx] <= '9') {
            answer = answer * 10 + (s[idx] - '0');
            idx++;
        } else {
            for (int d = 0; d < 10; d++) {
                const string& w = words[d];
                if (idx + (int)w.size() <= l && s.compare(idx, w.size(), w) == 0) {
                    answer = answer * 10 + d;
                    idx += w.size();
                    break;
                }
            }
        }
    }
    return answer;
}