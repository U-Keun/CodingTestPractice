#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    int n;
    cin >> n;

    int input;
    vector<int> dp;
    while (n-- > 0) {
        vector<int> numbers;
        while (cin >> input) {
            numbers.emplace_back(input);
            if (cin.peek() == '\n') break;
        }
        int l = numbers.size();
        dp.resize(l, 0);
        for (int i = 0; i < l; i++) {
            if (i == 0) {
                dp[i] = numbers[i];
                continue;
            }
            if (i == 1) {
                dp[i] = max(dp[0], numbers[1]);
            }
            dp[i] = max(dp[i - 1], dp[i - 2] + numbers[i]);
        }

        cout << dp[l - 1] << '\n';
    }

    return 0;
}