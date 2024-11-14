#include <iostream>
using namespace std;

int main() {
    int N;
    cin >> N;

    int sequence[1000];
    sequence[0] = 1;
    sequence[1] = 3;

    for (int i = 2; i < N; i++) {
        sequence[i] = (sequence[i - 1] + 2 * sequence[i - 2]) % 10007;
    }

    cout << sequence[N - 1] << endl;

    return 0;
}