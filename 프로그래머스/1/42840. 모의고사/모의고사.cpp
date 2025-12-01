#include <string>
#include <vector>

using namespace std;

vector<int> first = { 1, 2, 3, 4, 5 };
vector<int> second = { 2, 1, 2, 3, 2, 4, 2, 5 };
vector<int> third = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

int f1(const vector<int>& answers) {
    int n = answers.size(), cnt = 0;
    for (int i = 0; i < n; i++) {
        if (answers[i] == first[i % 5]) cnt++;
    }
    return cnt;
}
int f2(const vector<int>& answers) {
    int n = answers.size(), cnt = 0;
    for (int i = 0; i < n; i++) {
        if (answers[i] == second[i % 8]) cnt++;
    }
    return cnt;
}
int f3(const vector<int>& answers) {
    int n = answers.size(), cnt = 0;
    for (int i = 0; i < n; i++) {
        if (answers[i] == third[i % 10]) cnt++;
    }
    return cnt;
}

vector<int> solution(vector<int> answers) {
    int a = f1(answers), b = f2(answers), c = f3(answers);
    if (a == b && b == c) return { 1, 2, 3 };
    if (a == b) {
        if (a > c) return { 1, 2 };
        else return { 3 };
    }
    if (b == c) {
        if (b > a) return { 2, 3 };
        else return { 1 };
    }
    if (a == c) {
        if (a > b) return { 1, 3 };
        else return { 2 };
    }
    
    if (a > b) {
		if (a > c) return { 1 };
        else return { 3 };
    } else {
        if (b > c) return { 2 };
        else return { 3 };
    }
}