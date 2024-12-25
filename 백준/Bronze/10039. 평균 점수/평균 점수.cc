#include <iostream>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int answer = 0, score;
    REP(i, 0, 4) {
        cin >> score;
        answer += max(40, score);
    }

    cout << answer / 5;

    return EXIT_SUCCESS;
}