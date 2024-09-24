#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    string s;
    cin >> s;

    int idx;
    while ((idx = s.find("pi")) >= 0) s.replace(idx, 2, "  ");
    while ((idx = s.find("ka")) >= 0) s.replace(idx, 2, "  ");
    while ((idx = s.find("chu")) >= 0) s.replace(idx, 3, "   ");

    for (char c : s) {
        if (c != ' ') {
            cout << "NO\n";
            return 0;
        }
    }
    
    cout << "YES\n";

    return 0;
}