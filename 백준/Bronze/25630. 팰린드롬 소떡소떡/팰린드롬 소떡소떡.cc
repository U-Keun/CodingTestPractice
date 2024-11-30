#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int n;
    string input;
    cin >> n >> input;

    int answer = 0;
    REP(i, 0, n / 2 - 1) {
        if (input[i] != input[n - i - 1]) answer++;
    }

    cout << answer;

    return EXIT_SUCCESS;
}