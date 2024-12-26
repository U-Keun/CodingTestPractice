#include <iostream>
#include <string>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    string A, B;
    cin >> A >> B;
    int a = A.size(), b = B.size(), answer = 51;
    REP(i, 0, b - a) {
        int score = 0;
        REP(j, 0, a - 1) {
            if (A[j] != B[i + j]) score++;
        }
        answer = min(answer, score);
    }

    cout << answer;

    return EXIT_SUCCESS;
}