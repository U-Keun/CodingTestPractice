#include <iostream>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n, num; cin >> n;
    long long answer = 0;
    stack<int> s;
    while (n-- > 0) {
        cin >> num;
        while (!s.empty() && s.top() <= num) s.pop();
        s.push(num);
        answer += s.size() - 1;
    }

    cout << answer;

    return EXIT_SUCCESS;
}