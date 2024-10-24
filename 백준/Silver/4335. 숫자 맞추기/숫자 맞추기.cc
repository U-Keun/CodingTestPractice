#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    int num, min = 0, max = 11;
    cin >> num;
    cin.ignore();
    string response;
    while (num > 0) {
        getline(cin, response);

        if (response == "right on") {
            if (min > max || min > num || max < num) cout << "Stan is dishonest\n";
            else cout << "Stan may be honest\n";
            min = 0;
            max = 11;
        } else if (response == "too high") {
            if (max >= num) max = num - 1;
        } else { // "too low"
            if (min <= num) min = num + 1;
        }

        cin >> num;
        cin.ignore();
    }

    return 0;
}