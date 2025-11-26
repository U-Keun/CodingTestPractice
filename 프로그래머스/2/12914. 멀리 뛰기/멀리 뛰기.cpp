#include <string>
#include <vector>

using namespace std;

long long solution(int n) {
    long long prev = 1, cur = 1;
    while (--n) {
        long long tmp = (prev + cur) % 1234567;
        prev = cur;
        cur = tmp;
    }
    
    return cur;
}