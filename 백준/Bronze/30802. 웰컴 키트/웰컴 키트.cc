//
//  main.cpp
//  IntroductionOfC++
//
//  Created by u-keun song on 6/26/24.
//

#include <iostream>

using namespace std;

int main() {
    int N;
    
    cin >> N;
    
    int applicants[6];
    
    for (int &applicants : applicants) {
        cin >> applicants;
    }
    
    int shirts, pens;
    cin >> shirts >> pens;
    
    
    int answer = 0;
    for (int applicant : applicants) {
        answer += applicant / shirts;
        if (applicant % shirts != 0) {
            answer++;
        }
    }
    
    cout << answer << endl;
    cout << N / pens << " " << N % pens << endl;
    
    return 0;
}