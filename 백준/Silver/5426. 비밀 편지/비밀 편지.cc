#include <iostream>
#include <string>
#include <cmath>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n; cin >> n;
    string input;
    REP(i, 0, n - 1) {
        cin >> input;
        int n = static_cast<int>(sqrt(input.size())), idx = 0;
        vector<vector<char>> letter(n, vector<char>(n));
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                letter[i][j] = input[idx++];
            }
        }
        REP(i, 0, n - 1) {
            REP(j, 0, n - 1) cout << letter[i][j];
        }
        cout << '\n';
    }

    return EXIT_SUCCESS;
}