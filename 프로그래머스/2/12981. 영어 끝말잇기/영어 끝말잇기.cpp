#include <string>
#include <vector>
#include <map>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    map<string, int> rec;
    int turn = 1, counter = 1;
    char prev = NULL;
    vector<int> answer = { 0, 0 };
    for (string word : words) {
		rec[word]++;
        if (prev != NULL && (prev != word[0] || rec[word] > 1)) {
            answer[0] = counter;
            answer[1] = turn;
            break;
        }
        prev = word[word.size() - 1];
        counter++;
        if (counter > n) {
            counter = 1;
            turn++;
        }
    }

    return answer;
}