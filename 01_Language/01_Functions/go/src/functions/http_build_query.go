package functions

import "net/url"

func HttpBuildQuery(queryData map[string][]string) string {
	return url.Values(queryData).Encode()
}
