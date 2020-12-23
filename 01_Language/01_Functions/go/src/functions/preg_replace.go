package functions

import "regexp"

func PregReplace(pattern string, replacement string, subject string) string {
	p, err := regexp.Compile(pattern)
	if err != nil {
		return subject
	}
	return p.ReplaceAllString(subject, replacement)
}
