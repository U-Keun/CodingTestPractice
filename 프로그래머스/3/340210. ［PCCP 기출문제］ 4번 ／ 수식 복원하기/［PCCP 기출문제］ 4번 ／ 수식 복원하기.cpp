#include <string>
#include <vector>
#include <iostream>

using namespace std;

#define REP(i,a,b) for (int i = a; i <= b; i++)

int minMod = 2, maxMod = 9;
// 문자열 파싱 하면서 진법 확인..? -> vector<string> ?
vector<string> parseExpression(const string& expression) {
    string num = "";
    string op = "";
    
    vector<string> parsed;
    for (char c : expression) {
        if (isspace(c)) continue;
        
        if (isdigit(c)) {
            minMod = max(minMod, (c - '0') + 1);
            num += c;
        } else {
            if (!num.empty()) { 
                parsed.push_back(num);
                num = "";
            }
            
            if (c == '+' || c == '-') {
                op += c;
                parsed.push_back(op);
            } else if (c == 'X') {
                num += c;
            }
        }
    }
    if (!num.empty()) parsed.push_back(num);
    
    return parsed;
}

// 완전한 수식에 대해 진법을 확인하는 함수
void checkMod(vector<string>& info) {
    int target = stoi(info[3]);
    
    bool foundMinMod = false, add = (info[1] == "+");
    REP(i, minMod, maxMod) {
        int a = info[0].size() - 1, b = info[2].size() - 1, 
        	result = 0, res = 0, digit = 1;
        while (a >= 0 || b >= 0) {
            int val = res;
            res = 0;
            if (a >= 0) {
                val += (info[0][a--] - '0');
            }
            
            if (b >= 0) {
                if (add) {
                    val += (info[2][b--] - '0');
                } else {
                    val -= (info[2][b--] - '0');
                }
            }
            if (val < 0) {
                res = -1;
                val += i;
            } else if (val >= i) {
                res = 1;
                val -= i;
            }
            
            result += val * digit;
            digit *= 10;
        }
        result += res * digit;
        
        if (result == target) {
            if (!foundMinMod) {
                foundMinMod = true;
                minMod = i;
            }
        } else {
            if (foundMinMod) {
                maxMod = i - 1;
            	break;   
            }
        }
    }
}

// 주어진 진법에 대해 값을 계산해서 결과값을 변경하는 함수
string checkAnswer(vector<string>& info) {
    string eq = info[0] + " " + info[1] + " " + info[2] + " = ";
    bool add = (info[1] == "+");
    
    int prev = -1;
    REP(i, minMod, maxMod) {
        int a = info[0].size() - 1, b = info[2].size() - 1, 
        	result = 0, res = 0, digit = 1;
        while (a >= 0 || b >= 0) {
            int val = res;
            res = 0;
            if (a >= 0) {
                val += (info[0][a--] - '0');
            }
            
            if (b >= 0) {
                if (add) {
                    val += (info[2][b--] - '0');
                } else {
                    val -= (info[2][b--] - '0');
                }
            }
            if (val < 0) {
                res = -1;
                val += i;
            } else if (val >= i) {
                res = 1;
                val -= i;
            }
            
            result += val * digit;
            digit *= 10;
        }
        result += res * digit;
        
        if (prev == -1) {
            prev = result;
            continue;
        }
        
        if (prev != result) {
            eq += "?";
            return eq;
        }
    }
    
    eq += to_string(prev);
    
    return eq;
}


vector<string> solution(vector<string> expressions) {
    vector<vector<string>> infos;
    for (string expression : expressions) {
        vector<string> info = parseExpression(expression);
        if (info[3] == "X") infos.push_back(info);
        else checkMod(info);
    }
    
    vector<string> answer;
    for (vector<string> problem : infos) {
        answer.push_back(checkAnswer(problem));
    }
    
    return answer;
}