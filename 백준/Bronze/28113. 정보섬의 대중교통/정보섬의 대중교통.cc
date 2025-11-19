#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n, a, b;
    cin >> n >> a >> b;
    if (a == b) {
        if (n <= b) cout << "Anything\n";
        else cout << "Bus\n";
    } else if (a < b) cout << "Bus\n";
    else { 
        if (n <= b) cout << "Subway\n";
        else cout << "Bus\n"; 
    }

    return EXIT_SUCCESS;
}