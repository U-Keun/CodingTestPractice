# [Diamond V] Divine Divisor - 8187 

[문제 링크](https://www.acmicpc.net/problem/8187) 

### 성능 요약

메모리: 2328 KB, 시간: 2748 ms

### 분류

임의 정밀도 / 큰 수 연산, 수학, 밀러–라빈 소수 판별법, 정수론, 폴라드 로, 소수 판정

### 제출 일자

2025년 3월 9일 15:43:37

### 문제 설명

<p>An integer N > 1 is given. We say that an integer d > 1 is a divisor of N with multiplicity k > 0 (k is integer) if d^k | N and d^(k+1) does not divide N. For example, the number N=48=16x3 has the following divisors: 2 with multiplicity 4, 3 with multiplicity 1, 4 with multiplicity 2, 6 with multiplicity 1, and so on.</p>

<p>We say that a number  is a divine divisor of the number N if d is a divisor of N with multiplicity k and N has no divisors with multiplicities greater than k. For example, the sole divine divisor of 48 is 2 (with multiplicity 4), and the divine divisors of 6 are: 2, 3 and 6 (each with multiplicity 1).</p>

<p>Your task is to determine the multiplicity of divine divisors of N and the number of its divine divisors.</p>

### 입력 

 <p>The number N is given on the standard input, though in a somewhat unusual way. The first line holds a single integer n (1 ≤ n ≤ 600). The second line holds n integers a<sub>i</sub> (2 ≤ a<sub>i</sub> ≤ 10^18) separated by single spaces. These denote that N=a<sub>1</sub>⋅a<sub>2</sub>⋅…⋅a<sub>n</sub>.</p>

<p> </p>

### 출력 

 <p>The first line of the standard output should hold the maximum integer k such that there exists a divisor d of N such that d^k|N. The second line should hold a single integer D that is the number of (divine) divisors of N with multiplicity k.</p>

