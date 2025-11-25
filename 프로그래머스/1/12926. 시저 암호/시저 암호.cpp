#include <string>
#include <vector>

using namespace std;

string solution(string s, int n) {
    string answer = "";
    for (char c : s) {
        if ('a' <= c && c <= 'z') {
            int idx = ((c - 'a') + n) % 26;
            answer += ('a' + idx);
        } else if ('A' <= c && c <= 'Z') {
            int idx = ((c - 'A') + n) % 26;
            answer += ('A' + idx);
        } else answer += ' ';
    }
    return answer;
}