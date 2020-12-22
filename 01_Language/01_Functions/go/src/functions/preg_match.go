package functions

import "regexp"

func PregMatch(pattern string, subject string) bool {
	p, err := regexp.Compile(pattern)
	if err != nil {
		return false
	}
	return p.MatchString(subject)
}
