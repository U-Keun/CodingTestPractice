#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    string a, b;
    cin >> a >> b;

    vector<int> A, B;
    for (char digit : a) {
        A.emplace_back((int) digit - '0');
    }

    for (char digit : b) {
        B.emplace_back((int) digit - '0');
    }

    long long answer = 0;
    for (int aDigit : A) {
        for (int bDigit : B) {
            answer += aDigit * bDigit;
        }
    }

    cout << answer;
    return 0;
}