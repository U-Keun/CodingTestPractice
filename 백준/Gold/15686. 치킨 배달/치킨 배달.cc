#include <iostream>
#include <vector>
#include <stdlib.h>

using namespace std;

int n, m;

vector<pair<int, int>> houses;
vector<pair<int, int>> chickens;
vector<vector<int>> combinations;

void getCombinations(int from, vector<int> combination) {
    if (combination.size() == m) {
        combinations.push_back(combination);
        return;
    }
    for (int i = from + 1; i < chickens.size(); i++) {
        combination.push_back(i);
        getCombinations(i, combination);
        combination.pop_back();
    }
}

int main()
{
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);

    cin >> n >> m;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            int k;
            cin >> k;
            if (k == 1) {
                houses.push_back(pair(i, j));
            } else if (k == 2) {
                chickens.push_back(pair(i, j));
            }
        }
    }

    vector<int> combination;
    getCombinations(-1, combination);

    int answer = 10000001;
    for (vector<int> comb : combinations) {
        int tmp = 0;
        for (pair<int, int> house : houses) {
            int minTmp = 10000000;
            for (int e : comb) {
                pair<int, int> candidate = chickens[e];
                minTmp = min(minTmp, abs(house.first - candidate.first) + abs(house.second - candidate.second));
            }
            tmp += minTmp;
        }
        answer = min(answer, tmp);
    }

    cout << answer << '\n';
    return 0;
}