#include <iostream>

using namespace std;

int main() {
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);
    
    int a, m;
    cin >> a >> m;
    
    for (int i = 0; i < m; i++) {
        int product = a * i;
        if (product % m == 1) {
            cout << i << '\n';
            break;
        }
    }
    
    return 0;
}