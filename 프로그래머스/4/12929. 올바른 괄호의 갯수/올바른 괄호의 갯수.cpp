#include <string>
#include <vector>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int solution(int n) {
    vector<int> catalan(n + 1, 0);
    catalan[0] = 1;
    
    REP(i, 1, n) {
        REP(j, 1, i) {
            catalan[i] += catalan[j - 1] * catalan[i - j];
        }
    }
    
    return catalan[n];
}