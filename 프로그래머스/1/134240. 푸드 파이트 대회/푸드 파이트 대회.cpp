#include <string>
#include <vector>

using namespace std;

string solution(vector<int> food) {
    string left = "", right = "";
    int l = food.size();
    for (int i = 1; i < l; i++) {
        int k = food[i] / 2;
		for (int j = 0; j < k; j++) {
            char c = '0' + i;
            left += c;
            right = c + right;
        }
    }
    return left + '0' + right;
}