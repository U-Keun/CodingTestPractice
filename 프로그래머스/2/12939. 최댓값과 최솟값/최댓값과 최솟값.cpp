#include <string>
#include <vector>
#include <sstream>
#include <climits>

using namespace std;

vector<int> parse_int(const string& str) {
    vector<int> res;
    istringstream iss(str);
    int x;
    while (iss >> x) {
        res.push_back(x);
    }
    return res;
}

string solution(string s) {
    vector<int> nums = parse_int(s);
    int _min = INT_MAX, _max = INT_MIN;
    for (int num : nums) {
        if (num < _min) _min = num;
        if (num > _max) _max = num;
    }
    string answer = "";
    answer += to_string(_min);
    answer += " ";
    answer += to_string(_max);
    return answer;
}