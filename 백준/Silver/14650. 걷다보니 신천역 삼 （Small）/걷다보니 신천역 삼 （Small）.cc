#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int answer = 0, n;

void recursion(int cur, int len) {
    if (len == n) {
        if (cur % 3 == 0) answer++;
        return;
    }

    recursion(cur, len + 1);
    recursion(cur + 1, len + 1);
    recursion(cur + 2, len + 1);
}

int main() {
    FAST_IO

    cin >> n;
    recursion(1, 1);
    recursion(2, 1);

    cout << answer;

    return 0;
}