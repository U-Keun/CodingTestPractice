#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

vector<vector<bool>> record;

int main() {
    FAST_IO

    string input;
    cin >> input;

    int l = input.size();
    record.resize(l, vector<bool>(l, false));

    for (int i = 0; i < l; i++) record[i][i] = true;
    for (int i = 1; i < l; i++) record[i - 1][i] = (input[i - 1] == input[i]);

    for (int i = l - 3; i >= 0; i--) {
        for (int j = i + 2; j < l; j++) {
            record[i][j] = (record[i + 1][j - 1] && !(input[i] ^ input[j]));
        }
    }

    vector<int> dp(l + 1, l);
    dp[0] = 0;
    for (int j = 0; j < l; j++) {
        for (int i = 0; i <= j; i++) {
            if (!record[i][j]) continue;
            dp[j + 1] = min(dp[j + 1], dp[i] + 1);
            if (dp[j + 1] == 1) break;
        }
    }

    cout << dp[l];


    return EXIT_SUCCESS;
}