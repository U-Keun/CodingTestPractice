#include <iostream>
#include <vector>
#include <limits>

#define FAST_IO() \
    ios::sync_with_stdio(false); \
    cin.tie(NULL); \
    cout.tie(NULL);
#define ll long long
#define MAX 10000000
using namespace std;


class PrimeSieve {
    vector<bool> primeBoard;
    void updateBoard();
public:
    PrimeSieve(const int);
    bool isPrime(const int) const;
};

void PrimeSieve::updateBoard() {
    const int l = primeBoard.size();
    primeBoard[0] = false;
    primeBoard[1] = false;
    for (int i = 2; i < l; i++) {
        if (primeBoard[i]) {
            for (int j = i + i; j < l; j += i) {
                primeBoard[j] = false;
            }
        }
    }
}

PrimeSieve::PrimeSieve(const int size) {
    this->primeBoard.resize(size + 1, true);
    updateBoard();
}

bool PrimeSieve::isPrime(const int num) const {
    return primeBoard[num];
}

int main() {
// int algorithm() {
    FAST_IO();

    const PrimeSieve sieve(MAX);

    ll a, b;
    cin >> a >> b;

    int count = 0;
    for (ll i = 2; i < MAX; i++) {
        if (sieve.isPrime(i)) {
            ll primePower = i * i;
            while (primePower <= b) {
                if (primePower >= a) count++;
                if (primePower > std::numeric_limits<ll>::max() / i) break;
                primePower *= i;
            }
        }
    }

    cout << count << '\n';
    return 0;
}