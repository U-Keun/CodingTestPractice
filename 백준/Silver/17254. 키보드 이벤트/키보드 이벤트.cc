#include <iostream>
#include <tuple>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

struct compare {
    bool operator()(const tuple<int, int, char>& a, const tuple<int, int, char>& b) {
        if (get<1>(a) == get<1>(b)) {
            return get<0>(a) > get<0>(b);
        }
        return get<1>(a) > get<1>(b);
    }
};

int main() {
    FAST_IO

    int n, m; cin >> n >> m;
    priority_queue<tuple<int, int, char>, vector<tuple<int, int, char>>, compare> pq;
    int keyboard, time;
    char c;
    while (m-- > 0) {
        cin >> keyboard >> time >> c;
        pq.emplace(keyboard, time, c);
    }

    while (!pq.empty()) {
        char tmp = get<2>(pq.top());
        pq.pop();
        cout << tmp;
    }
    cout << '\n';

    return EXIT_SUCCESS;
}