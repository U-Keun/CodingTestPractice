# Process input
N = int(input())
prices = [0] + list(map(int, input().split()))

# Initialize dp array
dp = [0] * (N + 1)

# DP problem solving
for i in range(1, N + 1):
    max_value = prices[i]
    for j in range(1, i // 2 + 1):
        max_value = max(max_value, dp[j] + dp[i - j])
    dp[i] = max_value

print(dp[N])