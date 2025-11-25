#include <string>
#include <vector>

using namespace std;

#define MOD 1234567

int solution(int n) {
    int l = 0, r = 1, s = n / 2;
    while (s--) {
        l += r;
        r += l;
        l %= MOD;
        r %= MOD;
    }
    return (n % 2 == 1) ? r : l;
}