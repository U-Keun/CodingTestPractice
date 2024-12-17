#include <iostream>
#include <queue>

#define FAST_IO cin.tie(NULL)->ios::sync_with_stdio(false);

using namespace std;

class Number {
public:
    vector<char> num;
    int idx;
    Number(string str) {
        num.resize(100, '.');
        idx = 0;
        for (char c : str) {
            if (idx == 1 && num[0] == '0') {
                if (c != '0') num[0] = c;
                continue;
            }
            num[idx++] = c;
        }
    }

    bool operator < (const Number& other) const {
        if (this->idx != other.idx) return this->idx > other.idx;
        for (int i = 0; i < idx; ++i) {
            if (this->num[i] != other.num[i]) return this->num[i] > other.num[i];
        }
        return false;
    }

    string get_number() const {
        string ret = "";
        int i = 0;
        while (num[i] != '.') {
            ret += num[i++];
        }
        return ret;
    }
};

priority_queue<Number> pq;

int main() {
    FAST_IO

    int n; cin >> n;
    string line;
    while (n-- > 0) {
        cin >> line;
        string tmp = "";
        bool on = false;
        for (char c : line) {
            if (on) {
                if (c < '0' || c > '9') {
                    on = false;
                    Number number(tmp);
                    pq.push(number);
                    tmp = "";
                } else tmp += c;
            } else {
                if (c < '0' || c > '9') continue;
                tmp += c;
                on = true;
            }
        }
        if (tmp != "") {
            Number number(tmp);
            pq.push(number);
        }
    }

    while (!pq.empty()) {
        cout << pq.top().get_number() << '\n';
        pq.pop();
    }

    return EXIT_SUCCESS;
}