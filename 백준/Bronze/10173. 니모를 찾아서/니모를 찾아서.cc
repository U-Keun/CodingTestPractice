#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;


int main() {
    FAST_IO

    string input;
    getline(cin, input);
    while (input != "EOI") {
        for (char& c : input) {
            c = std::tolower(static_cast<unsigned char>(c));
        }
        int pos = input.find("nemo", 0);
        if (pos == -1) cout << "Missing\n";
        else cout << "Found\n";

        getline(cin, input);
    }

    return 0;
}