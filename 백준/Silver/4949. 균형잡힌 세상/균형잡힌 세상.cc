#include <iostream>
#include <string>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    string line;
    getline(cin, line);
    while (line != ".") {
        stack<char> pth;
        bool is_balanced = true;

        for (char c : line) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ' || c == '.') continue;

            if (c == '(' || c == '[') pth.push(c);
            else if (c == ')') {
                if (pth.empty() || pth.top() != '(') {
                    is_balanced = false;
                    break;
                } else pth.pop();
            } else if (c == ']') {
                if (pth.empty() || pth.top() != '[') {
                    is_balanced = false;
                    break;
                } else pth.pop();
            }
        }

        if (!is_balanced || !pth.empty()) cout << "no\n";
        else cout << "yes\n";

        getline(cin, line);
    }

    return EXIT_SUCCESS;
}