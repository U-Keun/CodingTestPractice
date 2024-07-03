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
    
    for (int i = 0; i < 6; i++) {
        cin >> applicants[i];
    }
    
    int shirtsAndPens[2];
    cin >> shirtsAndPens[0];
    cin >> shirtsAndPens[1];
    
    int shirts = 0;
    
    for (int i = 0; i < 6; i++) {
        shirts += applicants[i] / shirtsAndPens[0];
        if (applicants[i] % shirtsAndPens[0] != 0) {
            shirts++;
        }
    }
    
    cout << shirts << endl;
    cout << N / shirtsAndPens[1] << " " << N % shirtsAndPens[1] << endl;
    
}