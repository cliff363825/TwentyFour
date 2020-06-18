package functions

import (
	"io/ioutil"
	"net/http"
	"strings"
)

func FileGetContents(filename string) (string, error) {
	var bytes []byte
	var err error
	if s := strings.ToLower(filename); strings.HasPrefix(s, "http://") || strings.HasPrefix(s, "https://") {
		resp, err := http.Get(filename)
		if err != nil {
			return "", err
		}
		defer resp.Body.Close()
		bytes, err = ioutil.ReadAll(resp.Body)
	} else {
		bytes, err = ioutil.ReadFile(filename)
	}
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}
