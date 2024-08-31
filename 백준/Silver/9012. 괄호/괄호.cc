#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

bool isValidVPS(string& input, int idx, int balance) {
    if (idx == input.size()) {
        return balance == 0;
    }

    if (input[idx] == '(') {
        return isValidVPS(input, idx + 1, balance + 1);
    }

    if (balance == 0) return false;

    return isValidVPS(input, idx + 1, balance - 1);
}

int main() {
// int algorithm() {
    FAST_IO

    int t;
    cin >> t;

    string input;
    for (int i = 0; i < t; i++) {
        cin >> input;
        if (isValidVPS(input, 0, 0)) cout << "YES\n";
        else cout << "NO\n";
    }

    return 0;
}