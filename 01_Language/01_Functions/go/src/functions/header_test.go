package functions

import (
	"net/http"
	"testing"
)

func TestHeader(t *testing.T) {
	http.HandleFunc("/", func(resp http.ResponseWriter, req *http.Request) {
		Header(resp, "Cache-Control: no-cache, must-revalidate", false); // HTTP/1.1
		Header(resp, "Expires: Sat, 26 Jul 1997 05:00:00 GMT", false); // Date in the past
		//Header(resp, "Content-Type: application/json", false)
	})
	http.ListenAndServe("localhost:8080", nil)
}

