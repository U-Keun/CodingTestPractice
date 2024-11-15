#include <iostream>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int k;
    cin >> k;

    stack<int> s;
    int num;
    while (k-- > 0) {
        cin >> num;
        if (num == 0) {
            s.pop();
        } else s.push(num);
    }

    long long answer = 0;
    while (!s.empty()) {
        answer += s.top();
        s.pop();
    }

    cout << answer;

    return 0;
}