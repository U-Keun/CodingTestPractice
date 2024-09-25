#include <iostream>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    string input;
    cin >> input;
    stack<int> s;
    for (char c : input) s.push(c - '0');

    int digit = 1;
    stack<int> answer;
    while (!s.empty()) {
        int num = 0, val = 1;
        for (int i = 0; i < 3 && !s.empty(); i++) {
            num += s.top() * val;
            val *= 2;
            s.pop();
        }
        answer.push(num);
        digit *= 10;
    }

    while (!answer.empty()) {
        cout << answer.top();
        answer.pop();
    }

    return 0;
}