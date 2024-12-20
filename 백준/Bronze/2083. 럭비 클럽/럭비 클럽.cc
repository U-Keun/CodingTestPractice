#include <iostream>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

int main() {
    FAST_IO

    string name;
    int age, weight;
    cin >> name >> age >> weight;
    while (name != "#" && age != 0 && weight != 0) {
        cout << name << ' ';
        if (age > 17 || weight >= 80) cout << "Senior\n";
        else cout << "Junior\n";
        cin >> name >> age >> weight;
    }

    return EXIT_SUCCESS;
}