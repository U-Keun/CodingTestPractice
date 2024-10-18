#include <iostream>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int a, b;
    cin >> a >> b;

    long long answer = 0;
    REP(i, max(3, a), b) {
        if (i % 2 == 1) continue;
        answer += i;
    }
    
    cout << answer;
    
    return 0;
}