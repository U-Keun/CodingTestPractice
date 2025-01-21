#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    string input; cin >> input;
    int n = input.size();
    bool check = true;
    REP(i, 0, n - 1) {
        if (input[i] - '0' != i + 1) {
            check = false;
            break;
        }
    }

    if (check) cout << n;
    else cout << -1;

   return EXIT_SUCCESS;
}