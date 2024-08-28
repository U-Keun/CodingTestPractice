#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

unordered_map<long long, long long> record;

long long findRoom(long long preference) {
    if (record[preference] == 0) {
        record[preference] = preference + 1;
        return preference;
    }
    
    return record[preference] = findRoom(record[preference]);
}

vector<long long> solution(long long k, vector<long long> room_number) {
    vector<long long> answer;
    
    for (long long number : room_number) {
        long long registered = findRoom(number);
        answer.push_back(registered);
    }
    
    return answer;
}