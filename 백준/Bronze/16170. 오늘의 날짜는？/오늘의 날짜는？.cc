#include <ctime>
#include <iomanip>
#include <iostream>

using namespace std;

int main() {
    time_t t = time(nullptr);
    tm* utc = gmtime(&t);

    cout << (utc->tm_year + 1900) << '\n'
        << setw(2) << setfill('0') << (utc->tm_mon + 1) << '\n'
        << setw(2) << setfill('0') << utc->tm_mday << '\n';

    return EXIT_SUCCESS;
}