#include <iostream>
#include <algorithm>

#define FAST_IO() \
    ios::sync_with_stdio(false); \
    cin.tie(NULL); \
    cout.tie(NULL);

using namespace std;

int main() {
    FAST_IO();

    int n;
    cin >> n;

    int numbers[n];
    for (int i = 0; i < n; i++) {
        cin >> numbers[i];
    }
    sort(numbers, numbers + n);

    int answer = 1;
    for (int i = 0; i < n; i++) {
        if (numbers[i] <= answer) answer += numbers[i];
        else break;
    }

    cout << answer << '\n';

    return 0;
}