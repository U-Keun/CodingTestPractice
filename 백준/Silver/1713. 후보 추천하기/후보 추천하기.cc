#include <iostream>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false)
#define REP(i, a, b) for (int i = a; i < b; i++)

using namespace std;

struct photo {
    int id, votes, time;

    bool operator < (const photo& other) const {
        if (votes == other.votes) return time < other.time;
        return votes < other.votes;
    }
};

int main() {
    FAST_IO;

    int n, m, candi; cin >> n >> m;

    vector<photo> frame;

    REP(i, 0, m) {
        cin >> candi;

        bool found = false;
        for (auto& p : frame) {
            if (p.id == candi) {
                p.votes++;
                found = true;
                break;
            }
        }

        if (found) continue;

        if ((int) frame.size() < n) {
            frame.push_back(photo{ candi, 1, i });
            continue;
        }

        sort(frame.begin(), frame.end());
        frame[0] = { candi, 1, i };
    }

    sort(frame.begin(), frame.end(), [](const photo& a, const photo& b) {
        return a.id < b.id;
    });

    for (auto& p : frame) {
        cout << p.id << ' ';
    }
    cout << '\n';

    return EXIT_SUCCESS;
}