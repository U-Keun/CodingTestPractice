#include <iostream>
#include <cmath>

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

    const int digits = static_cast<int>(log10(abs(n))) + 1;

    for (int i = n - 9 * digits; i < n; i++) {
        if (i + getDigitSum(i) == n) {
            cout << i << '\n';
            return 0;
        }
    }

    cout << 0 << '\n';

    return 0;
}