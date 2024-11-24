#include <iostream>
#include <iomanip>
#include <string>
#include <unordered_map>
#include <stack>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int n;
    cin >> n;

    string equation;
    cin >> equation;

    unordered_map<char, double> subs;
    int num;
    for (char c = 'A'; c< 'A' + n; c++) {
        cin >> num;
        subs[c] = num;
    }

    stack<double> calc;
    for (char c : equation) {
        if (c >= 'A' && c <= 'Z') {
            calc.push(subs[c]);
            continue;
        }

        double a = calc.top(); calc.pop();
        double b = calc.top(); calc.pop();
        switch (c) {
            case '+':
                calc.push(a + b);
                break;
            case '-':
                calc.push(b - a);
                break;
            case '*':
                calc.push(a * b);
                break;
            case '/':
                calc.push(b / a);
                break;
        }
    }

    cout << fixed << setprecision(2) << calc.top();

    return EXIT_SUCCESS;
}