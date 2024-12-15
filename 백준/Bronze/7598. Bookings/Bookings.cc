#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    string plane;
    int booked;
    cin >> plane >> booked;
    while (plane != "#" || booked != 0) {
        char cmd;
        int num;
        cin >> cmd >> num;
        while (cmd != 'X' || num != 0) {
            if (cmd == 'B') {
                if (booked + num <= 68) booked += num;
            } else { // cmd == 'C'
                if (booked - num >= 0) booked -= num;
            }

            cin >> cmd >> num;
        }
        cout << plane << ' ' << booked << '\n';

        cin >> plane >> booked;
    }
    
    return EXIT_SUCCESS;
}