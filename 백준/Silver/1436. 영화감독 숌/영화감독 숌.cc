#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;

    int cnt = 0, num = 666;
    while (cnt != n) {
        if (to_string(num).find("666") != string::npos) cnt++;
        num++;
    }

    cout << num - 1;
    return EXIT_SUCCESS;
}