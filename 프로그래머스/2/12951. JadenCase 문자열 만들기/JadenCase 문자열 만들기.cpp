#include <string>
#include <vector>

using namespace std;

string solution(string s) {
    string answer = "";
    bool first = true;
    for (char c : s) {
        if (first) { 
        	if ('a' <= c && c <= 'z') answer += c - 'a' + 'A';
            else answer += c;
            if (c != ' ') first = false;
        } else {
            if (c == ' ') {
                first = true;
            }
            if ('A' <= c && c <= 'Z') answer += c - 'A' + 'a';
            else answer += c;
        }
    }
    
    return answer;
}