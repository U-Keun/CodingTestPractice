#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> solution(vector<string> strings, int n) {
    sort(strings.begin(), strings.end(), [&](const string& a, const string& b){
        if (a[n] == b[n]) return a < b;
        return a[n] < b[n];
    });
    return strings;
}