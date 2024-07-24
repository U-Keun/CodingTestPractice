#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int R1, S;
    cin >> R1 >> S;
    cout << 2 * S - R1 << '\n';

    return 0;
}