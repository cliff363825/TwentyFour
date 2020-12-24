package functions

import "regexp"

func PregReplaceCallback(pattern string, callback func(string)string, subject string) string {
	p, err := regexp.Compile(pattern)
	if err != nil {
		return subject
	}
	return p.ReplaceAllStringFunc(subject, callback)
}
