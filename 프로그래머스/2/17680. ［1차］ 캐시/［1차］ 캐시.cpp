#include <string>
#include <vector>
#include <list>
#include <algorithm>

using namespace std;

int solution(int cacheSize, vector<string> cities) {
    if (cacheSize == 0) return 5 * (int) cities.size();
    
    list<string> cache;
    int answer = 0;
    for (string city : cities) {
		for (char &c : city) c = tolower(c);
        auto it = find(cache.begin(), cache.end(), city);
        if (it == cache.end()) {
            answer += 5;
            if ((int) cache.size() == cacheSize) {
                cache.pop_back();
            }
        } else {
            answer += 1;
            cache.erase(it);
        }
        cache.push_front(city);
    }
    
    return answer;
}