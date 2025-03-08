#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO
        
    int l; cin >> l;
    int val = l / 5 + (l % 5 != 0);
    cout << val;

    return EXIT_SUCCESS;
}