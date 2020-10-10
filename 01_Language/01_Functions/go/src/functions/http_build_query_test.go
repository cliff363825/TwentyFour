package functions

import (
	"fmt"
	"testing"
)

func TestHttpBuildQuery(t *testing.T) {
	queryData := make(map[string][]string)
	queryData["foo"] = []string{"bar"}
	queryData["baz"] = []string{"boom"}
	queryData["cow"] = []string{"milk"}
	queryData["php"] = []string{"hypertext processor"}
	queryData["0"] = []string{"a", "b", "c"}
	fmt.Println(HttpBuildQuery(queryData))
}
