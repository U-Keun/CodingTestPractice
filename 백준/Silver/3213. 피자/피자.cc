#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;
    int counts[3] = { 0, 0, 0 };
    string input;
    while (n-- > 0) {
        cin >> input;
        if (input == "3/4") counts[2]++;
        else if (input == "1/2") counts[1]++;
        else counts[0]++;
    }

    int answer = 0;
    answer += min(counts[0], counts[2]);
    counts[0] -= answer;
    counts[2] -= answer;
    if (counts[2] > 0) answer += counts[2];
    answer += counts[1] / 2;
    counts[1] %= 2;
    if (counts[1] == 1) {
        answer++;
        counts[0] -= 2;
    }
    if (counts[0] > 0) answer += counts[0] / 4 + 1;
    cout << answer;

    return 0;
}