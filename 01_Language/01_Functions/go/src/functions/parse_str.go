package functions

import "net/url"

func ParseStr(str string) (url.Values, error) {
	return url.ParseQuery(str)
}
