package functions

import "regexp"

func Nl2br(s string) (string, error) {
	pattern, err := regexp.Compile("(\r\n|\n\r|\n|\r)")
	if err != nil {
		return "", err
	}
	return pattern.ReplaceAllString(s, "<br />$1"), nil
}
