package functions

import (
	"strings"
)

const (
	CASE_LOWER = 0
	CASE_UPPER = 1
)

func ArrayChangeKeyCase(m map[string]interface{}, caseType int) map[string]interface{} {
	res := make(map[string]interface{}, len(m))
	for k, v := range m {
		if caseType == CASE_LOWER {
			res[strings.ToLower(k)] = v
		} else {
			res[strings.ToUpper(k)] = v
		}
	}
	return res
}
