#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

ll n, m;

ll phi(ll n){
	ll ret = n;
	ll p = 2;
	while(p*p <= n){
		if(n%p == 0) ret = ret / p * (p - 1);
		while(n%p == 0) n /= p;
		p++;
	}
	if(n != 1) ret = ret / n * (n - 1);
	return ret;
}

ll pw(ll a, ll b, ll c){
	ll ret = 1;
	while(b){
		if(b & 1) ret = ret * a % c;
		b >>= 1;
		a = a * a % c;
	}
	return ret;
}

ll naive(ll n, ll m){
	if(n == 1) return 1;
	return pw(n, naive(n-1, m), m);
}

ll f(ll n, ll m){
	if(m == 1) return 1;

	if(n <= 5){
		return naive(n, m);
	}

	return pw(n, f(n-1, phi(m)) + phi(m), m);
}

int main(){
	ios_base::sync_with_stdio(0); cin.tie(0);
	cin >> n >> m;
	cout << f(n, m) % m;
}