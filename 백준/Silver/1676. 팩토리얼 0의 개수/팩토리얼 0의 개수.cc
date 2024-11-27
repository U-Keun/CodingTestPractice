#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO
    
    int n;
    cin >> n;
    int zeros = 0, count = 0;
    
    REP(i, 1, n) {
        int tmp = i;
        while (tmp % 2 == 0) {
            tmp /= 2;
            count++;
        }
        
        while (tmp % 5 == 0) {
            tmp /= 5;
            count--;
            zeros++;
        }
    }
    cout << zeros << '\n';
    
    return EXIT_SUCCESS;
}