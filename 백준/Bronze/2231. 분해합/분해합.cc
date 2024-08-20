#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int getDigitSum(int num) {
    int answer = 0;
    while (num > 0) {
        answer += num % 10;
        num /= 10;
    }
    return answer;
}

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    for (int i = 1; i < n; i++) {
        int value = i + getDigitSum(i);
        if (value == n) {
            cout << i << '\n';
            return 0;
        }
    }

    cout << 0 << '\n';

    return 0;
}