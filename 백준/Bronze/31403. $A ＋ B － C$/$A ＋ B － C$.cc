#include <iostream>
#include <string>

using namespace std;

int main() {
    string a,b,c;
    
    cin >> a;
    cin >> b;
    cin >> c;
    
    int A = stoi(a);
    int B = stoi(b);
    int AB = stoi(a + b);
    int C = stoi(c);
    
    cout << A + B - C << endl;
    cout << AB - C << endl;
    
    return 0;
}