# coding: utf-8

def levenshtein(str1, str2, cost_ins=1, cost_rep=1, cost_del=1):
    len1, len2 = len(str1), len(str2)
    if len1 == 0:
        return len2
    if len2 == 0:
        return len1

    if len1 > len2:
        str1, str2 = str2, str1
        len1, len2 = len2, len1
        cost_ins, cost_del = cost_del, cost_ins

    dp = list(range(0, len1 + 1))

    # j from 1 to len2+1
    for j in range(1, len2 + 1):
        prev = dp[0]
        dp[0] = j

        # i from 1 to len1+1
        for i in range(1, len1 + 1):
            curr = dp[i]
            dp[i] = min(dp[i - 1] + cost_del, dp[i] + cost_ins, prev + (0 if str2[j - 1] == str1[i - 1] else cost_rep))
            prev = curr

    return dp[len1]


if __name__ == '__main__':
    str1 = 'carrrot1'
    str2 = 'carrots'
    print(levenshtein(str1, str2))
    print(levenshtein(str2, str1))
    print(levenshtein(str1, str2, 2, 3, 4))
    print(levenshtein(str2, str1, 2, 3, 4))
