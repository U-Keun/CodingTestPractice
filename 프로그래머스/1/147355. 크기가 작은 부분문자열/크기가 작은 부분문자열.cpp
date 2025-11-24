#include <string>
#include <vector>

using namespace std;

int solution(string t, string p) {
    int t_size = t.size(), p_size = p.size(), answer = 0;
    for (int i = 0; i < t_size - p_size + 1; i++) {
        if (stoll(t.substr(i, p_size)) <= stoll(p)) answer++;
    }
    return answer;
}