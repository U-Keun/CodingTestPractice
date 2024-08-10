#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> triangle) {
    int answer = 0;
    int l = triangle.size();
    for (int i = 1; i < l; i++) {
        for (int j = 0; j < i + 1; j++) {
            if (j == 0 || j == i) {
                triangle[i][j] += (j == 0) ? triangle[i - 1][0] : triangle[i - 1][j - 1];
                continue;
            }
            triangle[i][j] += max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            if (i == l - 1) answer = max(answer, triangle[i][j]);
        }
    }
    
    return answer;
}