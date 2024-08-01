#include <iostream>
#include <stack>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO;
    int n;
    cin >> n;

    string input1, input2;
    for (int i = 0; i < n; ++i) {
        cin >> input1 >> input2;

        stack<bool> answer;
        bool prev = false;

        int len1 = input1.size(), len2 = input2.size();
        int max_len = max(len1, len2);

        for (int j = 0; j < max_len; ++j) {
            bool bit1 = j < len1 ? (input1[len1 - 1 - j] == '1') : false;
            bool bit2 = j < len2 ? (input2[len2 - 1 - j] == '1') : false;

            bool current;
            if (bit1 && bit2) {
                current = prev;
                prev = true;
            } else if (bit1 || bit2) {
                current = !prev;
            } else {
                current = prev;
                prev = false;
            }

            answer.push(current);
        }

        if (prev) {
            answer.push(true);
        }

        while (!answer.empty() && !answer.top()) answer.pop();

        if (answer.empty()) cout << 0;
        else {
            while (!answer.empty()) {
                cout << answer.top();
                answer.pop();
            }
        }

        cout << '\n';
    }

    return 0;
}