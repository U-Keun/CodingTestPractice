#include <iostream>
#include <string>
#include <sstream>
#include <deque>
using namespace std;

class AC {
private:
    deque<int> array;
    bool isReversed;
public:
    AC(const deque<int>& arr, bool rev) : array(arr), isReversed(rev) {}

    // 입력 문자열 형식은 "[1,2,3,...]" 와 같이 주어진다고 가정합니다.
    static AC create(const string &input) {
        deque<int> tmp;
        if (input == "[]")
            return AC(tmp, false);
        // 양 끝의 대괄호를 제거합니다.
        string s = input.substr(1, input.size() - 2);
        stringstream ss(s);
        string token;
        while(getline(ss, token, ',')) {
            // 공백 제거 후 정수로 변환
            tmp.push_back(stoi(token));
        }
        return AC(tmp, false);
    }

    // R 명령: 현재 순서를 반전시킴
    void R() {
        isReversed = !isReversed;
    }

    // D 명령: isReversed 상태에 따라 앞이나 뒤에서 하나 제거
    // 만약 제거할 원소가 없다면 false를 반환
    bool D() {
        if (array.empty())
            return false;
        if (isReversed)
            array.pop_back();
        else
            array.pop_front();
        return true;
    }

    // 최종 결과를 문자열로 반환 (배열의 요소들을 순서대로 나열)
    string print() {
        string result = "[";
        if (isReversed) {
            while (!array.empty()) {
                int value = array.back();
                array.pop_back();
                result += to_string(value);
                if (!array.empty())
                    result += ",";
            }
        } else {
            while (!array.empty()) {
                int value = array.front();
                array.pop_front();
                result += to_string(value);
                if (!array.empty())
                    result += ",";
            }
        }
        result += "]";
        return result;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    // consume the newline after T
    cin.ignore();

    string output;
    for (int i = 0; i < T; i++) {
        // 명령어 문자열 읽기 (예: "RDD")
        string p;
        getline(cin, p);

        // 정수의 개수 읽기
        int n;
        cin >> n;
        cin.ignore(); // 개행 문자 소비

        // 배열 입력 문자열 읽기 (예: "[1,2,3,4]")
        string arrStr;
        getline(cin, arrStr);

        AC program = AC::create(arrStr);
        bool getError = false;

        // 각 명령어를 순차적으로 처리
        for (char c : p) {
            if (c == 'R')
                program.R();
            else if (c == 'D') {
                if (!program.D()) {
                    getError = true;
                    break;
                }
            }
        }

        if (getError)
            output += "error\n";
        else
            output += program.print() + "\n";
    }
    cout << output;
    return 0;
}