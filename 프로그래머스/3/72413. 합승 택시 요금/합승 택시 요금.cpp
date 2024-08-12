#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    vector<vector<int>> matrix(n, vector<int>(n, 0));
    for (const auto& edge : fares) {
        matrix[edge[0] - 1][edge[1] - 1] = edge[2];
        matrix[edge[1] - 1][edge[0] - 1] = edge[2];
    }
    
    for (int i = 0; i < n; i++) {
        for (int j = i + 1 ; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][j] = 20000001;
                matrix[j][i] = 20000001;
            }
        }
    }
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                matrix[j][k] = min(matrix[j][k], matrix[j][i] + matrix[i][k]);
            }
        }
    }
    
    int answer = 20000001;
    for (int i = 0; i < n; i++) {
        answer = min(answer, matrix[s - 1][i] + matrix[i][a - 1] + matrix[i][b - 1]);
    }
    
    return answer;
}