#include <iostream>

using namespace std;

int main() {
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);
    
    int n, m;
    cin >> n >> m;
    
    int garden[n + 1][m + 1];
    
    for (int i = 1; i <= n; i++) {
        cin >> garden[i][0];
    }
    
    for (int i = 1; i <= m; i++) {
        cin >> garden[0][i];
    }
    
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            garden[i][j] = garden[i - 1][j] ^ garden[i][j - 1];
        }
    }
    
    cout << garden[n][m] << "\n";
    
    return 0;
}