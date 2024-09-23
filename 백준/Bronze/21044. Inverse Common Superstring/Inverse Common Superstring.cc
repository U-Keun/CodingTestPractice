#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    string input;
    cin >> input;

    int maxA = 0, cur = 0;
    for (char c : input) {
        if (c == 'a') {
           cur++;
        } else {
            maxA = max(maxA, cur);
            cur = 0;
        }
    }
    maxA = max(maxA, cur);

    for (int i = 0; i <= maxA; i++) cout << 'a';

    return 0;
}