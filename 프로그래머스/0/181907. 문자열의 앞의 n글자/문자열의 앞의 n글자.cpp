#include <string>
#include <vector>

using namespace std;

string solution(string my_string, int n) {
    string answer = "";
    vector<char> str(my_string.begin(), my_string.end());
    int idx = 0;
    while (n--) {
        answer += str[idx];
        idx++;
    }
    return answer;
}