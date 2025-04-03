#include <iostream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    int s1, s2; cin >> s1 >> s2;
    bool sample = true, system = true;
    long long a, b;
    for (int i = 0; i < s1; i++) {
        cin >> a >> b;
        if (a == b) continue;
        sample = false;
    }

    for (int i = 0; i < s2; i++) {
        cin >> a >> b;
        if (a == b) continue;
        system = false;
    }

    if (!sample) cout << "Wrong Answer\n";
    else if (!system) cout << "Why Wrong!!!\n";
    else cout << "Accepted\n";

    return EXIT_SUCCESS;
}