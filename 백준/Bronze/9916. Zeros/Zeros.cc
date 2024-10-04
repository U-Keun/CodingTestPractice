#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);
#define REP(i,a,b) for (int i = a; i <= b; i++)

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;

    vector<int> number(200);
    number[0] = 1;

    int idx = 0, val, over;
    REP(i, 2, n) {
        over = 0;
        REP(j, 0, idx) {
            val = number[j] * i + over;
            over = val / 10;
            number[j] = val % 10;
        }

        while (over > 0) {
            number[++idx] = over % 10;
            over /= 10;
        }
    }

    int answer = 0;
    REP(i, 0, idx) {
        if (number[i] == 0) answer++;
    }
    
    cout << answer;
    return 0;
}