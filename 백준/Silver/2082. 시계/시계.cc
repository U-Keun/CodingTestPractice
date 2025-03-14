#include <iostream>
#include <unordered_map>
#include <string>
#include <sstream>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)

using namespace std;

unordered_map<int, string> numbers = {
    { 0, "####.##.##.####" },
    { 1, "..#..#..#..#..#" },
    { 2, "###..#####..###" },
    { 3, "###..####..####" },
    { 4, "#.##.####..#..#" },
    { 5, "####..###..####" },
    { 6, "####..####.####" },
    { 7, "###..#..#..#..#" },
    { 8, "####.#####.####" },
    { 9, "####.####..####" },
};

int may_be(const string& input) {
    for (int i = 0; i < 10; i++) {
        string num = numbers[i];
        bool check = true;
        for (int i = 0; i < 15; i++) {
            if (input[i] == num[i] || input[i] == '.') continue;
            check = false;
            break;
        }
        if (check) return i;
    }
    return -1;
}

int main() {
    FAST_IO;

    string input[4], line;

    for (int i = 0; i < 5; i++) {
        getline(cin, line);
        stringstream ss(line);
        string segment;
        for (int i = 0; i < 4; i++) {
            ss >> segment;
            input[i] += segment;
        }
    }

    int clock[4];
    for (int i = 0; i < 4; i++) {
        clock[i] = may_be(input[i]);
    }

    cout << clock[0] << clock[1] << ':'
        << clock[2] << clock[3] << '\n';

    return EXIT_SUCCESS;
}