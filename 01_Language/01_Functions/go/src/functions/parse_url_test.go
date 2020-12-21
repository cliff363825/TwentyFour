package functions

import (
	"fmt"
	"testing"
)

func TestParseUrl(t *testing.T) {
	url, _ := ParseUrl("http://username:password@hostname:9090/path?arg=value#anchor")
	fmt.Println(url.Scheme)
	fmt.Println(url.User.Username())
	fmt.Println(url.User.Password())
	fmt.Println(url.Hostname())
	fmt.Println(url.Port())
	fmt.Println(url.Path)
	fmt.Println(url.RawQuery)
	fmt.Println(url.Fragment)
}

