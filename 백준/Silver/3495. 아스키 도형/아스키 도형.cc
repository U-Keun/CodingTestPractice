#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int h, w; cin >> h >> w;
    char c;
    int answer = 0;
    REP(i, 0, h - 1) {
        bool on = false;
        REP(j, 0, w - 1) {
            cin >> c;
            if (!on) {
                if (c != '.') {
                    on = true;
                    answer += 1;
                }
            } else {
                if (c == '.') {
                    answer += 2;
                } else {
                    answer += 1;
                    on = false;
                }
            }
        }
    }

    cout << (answer / 2);

    return EXIT_SUCCESS;
}