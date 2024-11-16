#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    char c;
    int answer = 0;
    REP(i, 0, 7) {
        REP(j, 0, 7) {
            cin >> c;
            if (c == '.' || (i + j) % 2 == 1) continue;
            answer++;
        }
    }

    cout << answer;

    return 0;
}