#include <iostream>
#include <map>
#include <string>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

int main() {
    FAST_IO;

    string input; cin >> input;
    int size;
    while (input != "*") {
        size = input.size();
        map<string, int> record;
        bool check = true;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                string cur = to_string(input[j])  + to_string(input[j + i]);
                record[cur]++;
                if (record[cur] > 1) {
                    check = false;
                    break;
                }
            }
            if (!check) break;
            record.clear();
        }
        cout << input << " is ";
        if (!check) cout << "NOT ";
        cout << "surprising.\n";;

        cin >> input;
    }

    return EXIT_SUCCESS;
}