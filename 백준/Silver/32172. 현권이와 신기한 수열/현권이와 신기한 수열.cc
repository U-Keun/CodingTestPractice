#include <iostream>
#include <map>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int n; cin >> n;
    map<int, int> record;
    int answer = 0, i = 0;;
    record[answer]++;

    while (n--) {
        i++;
        if (answer - i < 0 || record[answer - i] > 0) { answer = answer + i; } 
        else answer -= i;
        record[answer]++;
    }

    cout << answer << '\n';

    return EXIT_SUCCESS;
}