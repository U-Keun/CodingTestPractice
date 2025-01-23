#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    string val; cin >> val;
    int answer = stoi(val, nullptr, 16);
    cout << answer;

   return EXIT_SUCCESS;
}