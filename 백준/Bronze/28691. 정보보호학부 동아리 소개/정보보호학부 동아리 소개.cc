#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    char c; cin >> c;
    switch (c) {
        case 'M' :
            cout << "MatKor\n";
            break;
        case 'W' :
            cout << "WiCys\n";
            break;
        case 'C' :
            cout << "CyKor\n";
            break;
        case 'A' :
            cout << "AlKor\n";
            break;
        case '$' :
            cout << "$clear\n";
            break;
    }

    return EXIT_SUCCESS;
}