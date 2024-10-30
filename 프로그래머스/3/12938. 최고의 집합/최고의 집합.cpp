#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
    vector<int> answer;
    int cnt = n;
    while (s > 0) {
        int tmp = s / cnt;
        if (tmp == 0) break;
        answer.push_back(tmp);
        s -= tmp;
        cnt--;
    }
    
    if (answer.size() != n) return { -1 };
    return answer;
}