#include <iostream>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int a, b;
    cin >> a >> b;
    
    if (b <= 3) {
        cout << 0;
        return 0;
    }
    
    if (a % 2 == 1) a++;
    if (b % 2 == 1) b--;
    a = max(a, 4);
    b = max(b, 4);
    
    if (a > b) {
        cout << 0;
        return 0;
    }

    long long answer = (long long) (a + b) * ((b - a) / 2 + 1) / 2;

    cout << answer;

    return 0;
}