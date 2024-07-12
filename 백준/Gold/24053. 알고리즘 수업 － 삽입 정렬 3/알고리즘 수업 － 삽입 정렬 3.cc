#include <iostream>

using namespace std;

int main() {
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);
    
    int N;
    cin >> N;
    
    int array[N];
    for (int i = 0; i < N; i++) {
        cin >> array[i];
    }
    
    int target[N];
    for (int i = 0; i < N; i++) {
        cin >> target[i];
    }
    
    for (int i = 1; i < N; i++) {
        int loc = i - 1;
        int new_item = array[i];
        
        while (0 <= loc && new_item < array[loc]) {
            array[loc + 1] = array[loc];
            if (equal(array, array + N, target)) {
                cout << 1 << '\n';
                return 0;
            }
            loc--;
        }
        if (loc + 1 != i) {
            array[loc + 1] = new_item;
            if (equal(array, array + N, target)) {
                cout << 1 << '\n';
                return 0;
            }
        }
    }
    
    cout << 0 << '\n';
    
    return 0;
}