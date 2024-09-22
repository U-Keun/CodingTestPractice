#include <iostream>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

void f(stack<char>& s, int n) {
    if (n == 0) return;
    if (n % 2 == 0) s.push('7');
    else s.push('4');
    f(s, (n - 1) / 2);
}

int main() {
// int algorithm() {
    FAST_IO

    int k;
    cin >> k;

    stack<char> s;
    f(s, k);

    while (!s.empty()) {
        cout << s.top();
        s.pop();
    }

    return 0;
}