#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int pow_(int num, int pow) {
    if (!pow) return 1;
    int ret = 1;
    while (pow-- > 0) ret *= num;
    return ret;
}

int main() {
    FAST_IO

    int t, num, idx; cin >> t;
    while (t-- > 0) {
        cin >> idx >> num;
        int oct = 0, dec = 0, hex = 0, pow = 0;
        bool valid_oct = true;
        while (num > 0) {
            int val = num % 10; num /= 10;
            if (val > 7) valid_oct = false;
            oct += val * pow_(8, pow);
            dec += val * pow_(10, pow);
            hex += val * pow_(16, pow);
            pow++;
        }
        cout << idx << ' ';
        if (valid_oct) cout << oct << ' ';
        else cout << 0 << ' ';
        cout << dec << ' ' << hex << '\n';
    }

    return EXIT_SUCCESS;
}