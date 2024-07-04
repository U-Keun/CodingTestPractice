//
//  main.cpp
//  IntroductionOfC++
//
//  Created by u-keun song on 6/26/24.
//

#include <iostream>
#include <string>

using namespace std;

int main() {
    string first, second, third;
    
    cin >> first >> second >> third;
    
    int current = 0;
    
    if (first.back() != 'z') {
        current = stoi(first);
    }
    
    if (second.back() != 'z') {
        current = stoi(second);
    } else {
        current++;
    }
    
    if (third.back() != 'z') {
        current = stoi(third);
    } else {
        current++;
    }
    
    current++;
    
    if (current % 3 == 0 && current % 5 == 0) {
        cout << "FizzBuzz" << endl;
    } else if (current % 3 == 0) {
        cout << "Fizz" << endl;
    } else if (current % 5 == 0) {
        cout << "Buzz" << endl;
    } else {
        cout << current << endl;
    }
    
    return 0;
}