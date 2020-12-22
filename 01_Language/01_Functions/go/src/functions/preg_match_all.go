package functions

import (
	"regexp"
)

func PregMatchAll(pattern string, subject string, matches *[][]string) bool {
	compile, err := regexp.Compile(pattern)
	if err != nil {
		return false
	}
	matchList := compile.FindAllStringSubmatch(subject, -1)
	if len(matchList) == 0 {
		return false
	}
	if matches == nil {
		matches = new([][]string)
	}
	for _, items := range matchList {
		for k, v := range items {
			if k > len(*matches)-1 {
				*matches = append(*matches, []string{})
			}
			(*matches)[k] = append((*matches)[k], v)
		}
	}
	return true
}
