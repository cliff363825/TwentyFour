package functions

import "regexp"

func PregGrep(pattern string, array []string) ([]string, error) {
	compile, err := regexp.Compile(pattern)
	if err != nil {
		return nil, err
	}

	var res []string
	for _, v := range array {
		if compile.MatchString(v) {
			res = append(res, v)
		}
	}
	return res, nil
}
