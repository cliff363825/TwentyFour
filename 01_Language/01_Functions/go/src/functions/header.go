package functions

import (
	"net/http"
	"strings"
)

func Header(resp http.ResponseWriter, string string, replace bool) {
	data := strings.SplitN(string, ":", 2)
	if len(data) < 2 {
		return
	}
	for k, v := range data {
		v = strings.TrimSpace(v)
		data[k] = v
	}
	if replace {
		resp.Header().Set(data[0], data[1])
	} else {
		resp.Header().Add(data[0], data[1])
	}
}
