#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

long long solution(int n, int m, int x, int y, vector<vector<int>> queries) {
    long long rowLeft = x, rowRight = x, colLeft = y, colRight = y;
    
    int l = queries.size();
    for (int i = l - 1; i >= 0; i--) {
        switch(queries[i][0]) {
            case 0:
                if (colLeft != 0) colLeft += queries[i][1];
                colRight += queries[i][1];
                if (colLeft >= m) return 0;
                colRight = (m - 1 < colRight) ? m - 1 : colRight;
                break;
            case 1:
                colLeft -= queries[i][1];
                if (colRight != m - 1) colRight -= queries[i][1];
                if (colRight < 0) return 0;
                colLeft = (colLeft < 0) ? 0 : colLeft;
                break;
            case 2:
                if (rowLeft != 0) rowLeft += queries[i][1];
                rowRight += queries[i][1];
                if (rowLeft >= n) return 0;
                rowRight = (n - 1 < rowRight) ? n - 1 : rowRight;
                break;
            case 3:
                rowLeft -= queries[i][1];
                if (rowRight != n - 1) rowRight -= queries[i][1];
                if (rowRight < 0) return 0;
                rowLeft = (rowLeft < 0) ? 0 : rowLeft;
                break;
        }
    }

    return (rowRight - rowLeft + 1) * (colRight - colLeft + 1);   
}