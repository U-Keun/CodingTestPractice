#include <iostream>

using namespace std;

int main() {
    int a, b; cin >> a >> b;
    cout << (b + 24 - a) % 24 << '\n';
}