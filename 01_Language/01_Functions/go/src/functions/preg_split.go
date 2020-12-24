package functions

import "regexp"

func PregSplit(pattern string, subject string, limit int) []string {
	compile, err := regexp.Compile(pattern)
	if err != nil {
		return nil
	}
	return compile.Split(subject, limit)
}
