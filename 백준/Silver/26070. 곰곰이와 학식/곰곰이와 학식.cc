#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)
#define REP(i, a, b) for (int i = a; i <= b; i++)

using namespace std;

vector<long long> needs(3), tickets(3);
long long answer = 0;

inline long long match_tickets(long long &need, long long &ticket) {
    long long used = min(need, ticket);
    need -= used;
    ticket -= used;
    return used;
}

inline long long convert_tickets(long long &from, long long &to) {
    long long converted = from / 3;
    to += converted;
    from %= 3;
    return converted;
}

bool match_and_convert(int i) {
    bool changed = false;

    long long used = match_tickets(needs[i], tickets[i]);
    if (used) {
        answer += used;
        changed = true;
    }

    long long converted = convert_tickets(tickets[i], tickets[(i + 1) % 3]);
    if (converted) {
        changed = true;
    }

    return changed;
}

int main() {
    FAST_IO;
    
    REP(i, 0, 2) cin >> needs[i];
    REP(i, 0, 2) cin >> tickets[i];

    while (true) {
        bool changed = false;

        REP(i, 0, 2) {
            if (match_and_convert(i)) changed = true;
        }

        if (!changed) break;
    }
        
    cout << answer << '\n';
   
    return EXIT_SUCCESS;
}