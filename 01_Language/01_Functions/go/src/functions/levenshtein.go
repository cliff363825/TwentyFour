package functions

import "math"

func Levenshtein(str1, str2 string, costIns, costRep, costDel int) int {
	len1, len2 := len(str1), len(str2)
	if len1 == 0 {
		return len2
	}
	if len2 == 0 {
		return len1
	}
	if len1 > len2 {
		len1, len2 = len2, len1
		str1, str2 = str2, str1
		costIns, costDel = costDel, costIns
	}

	dp := make([]int, len1+1)

	// init...
	for i := 0; i < len1+1; i++ {
		dp[i] = i
	}

	// j from 1 to len2+1
	for j := 1; j < len2+1; j++ {
		prev := dp[0]
		dp[0] = j

		// i from 1 to len1+1
		for i := 1; i < len1+1; i++ {
			curr := dp[i]
			tmpCostRep := costRep
			if str1[i - 1] == str2[j - 1] {
				tmpCostRep = 0
			}
			dp[i] = int(math.Min(math.Min(float64(dp[i-1]+costDel), float64(dp[i]+costIns)), float64(prev + tmpCostRep)))
			prev = curr
		}
	}

	return dp[len1]
}
