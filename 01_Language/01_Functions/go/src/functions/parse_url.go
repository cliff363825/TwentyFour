package functions

import url2 "net/url"

func ParseUrl(url string) (*url2.URL, error) {
	return url2.Parse(url)
}
