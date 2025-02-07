#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;
#define REP(i, a, b) for (int i = a; i <= b; i++)

int main() {
    FAST_IO

    int numbers[3];
    REP(i, 0, 2) cin >> numbers[i];
    if (numbers[0] > numbers[1]) swap(numbers[0], numbers[1]);
    if (numbers[1] > numbers[2]) swap(numbers[1], numbers[2]);
    if (numbers[0] > numbers[1]) swap(numbers[0], numbers[1]);
    
    char c;
    REP(i, 0, 2) {
        cin >> c;
        cout << numbers[c - 'A'] << ' ';
    }

    return EXIT_SUCCESS;
}