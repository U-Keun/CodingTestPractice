#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
// int algorithm() {
    FAST_IO

    float w, h;
    cin >> w >> h;

    float bmi = w / (h * h);
    if (bmi > 25.0) {
        cout << "Overweight\n";
    } else if (bmi < 18.5) {
        cout << "Underweight\n";
    } else {
        cout << "Normal weight\n";
    }

    return 0;
}