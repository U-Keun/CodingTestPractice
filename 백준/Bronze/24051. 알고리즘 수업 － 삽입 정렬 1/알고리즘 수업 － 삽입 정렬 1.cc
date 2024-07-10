#include <iostream>

using namespace std;

int main() {
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);
    
    int N, K;
    cin >> N >> K;
    
    int array[N];
    for (int i = 0; i < N; i++) {
        cin >> array[i];
    }
    
    for (int i = 1; i < N; i++) {
        int loc = i - 1;
        int new_item = array[i];
        
        while (0 <= loc && new_item < array[loc]) {
            array[loc + 1] = array[loc];
            if (--K == 0) {
                cout << array[loc + 1] << '\n';
            }
            loc--;
        }
        if (loc + 1 != i) {
            array[loc + 1] = new_item;
            if (--K == 0) {
                cout << array[loc + 1] << '\n';
            }
        }
    }
    
    if (K > 0) {
        cout << -1 << '\n';
    }
}