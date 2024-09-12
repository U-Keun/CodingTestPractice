#include <iostream>
#include <vector>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

void convertToBit(vector<bool>& bit, long long number) {
    if (number == 0) return;

    int val = number % 2;
    convertToBit(bit, number / 2);
    bit.push_back(val);
}

int main() {
// int algorithm() {
    FAST_IO

    long long n;
    cin >> n;

    vector<bool> bit;
    convertToBit(bit, n);

    for (bool d : bit) {
        if (d) cout << 1;
        else cout << 0;
    }

    return 0;
}