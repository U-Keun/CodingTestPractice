#include <iostream>
#include <algorithm>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

bool isTriangle(int a, int b, int c) {
    return a > b + c;
}

int main() {
    FAST_IO
        
    int n;
    cin >> n;
    
    vector<int> numbers(n);
    REP(i, 0, n - 1) cin >> numbers[i];
    
    sort(numbers.begin(), numbers.end(), greater<>());
    
    bool done = false;
    REP(i, 0, n - 3) {
        if (numbers[i] >= numbers[i + 1] + numbers[i + 2]) continue;
        cout << (numbers[i] + numbers[i + 1] + numbers[i + 2]);
        done = true;
        break;
    }
    
    if (!done) cout << -1;
    
    return 0;
}