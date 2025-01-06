#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

struct Lecture {
    int start, end;
};

bool cmp(const Lecture &a, const Lecture &b) {
    if (a.start == b.start) {
        return a.end < b.end;
    }
    return a.start < b.start;
}

int main() {
    FAST_IO

    int N; cin >> N;

    vector<Lecture> lectures(N);
    for (int i = 0; i < N; i++) {
        cin >> lectures[i].start >> lectures[i].end;
    }

    sort(lectures.begin(), lectures.end(), cmp);

    priority_queue<int, vector<int>, greater<>> endTimes;

    for (auto &lec : lectures) {
        if (!endTimes.empty() && endTimes.top() <= lec.start) {
            endTimes.pop();
        }
        endTimes.push(lec.end);
    }

    cout << endTimes.size() << "\n";

    return EXIT_SUCCESS;
}