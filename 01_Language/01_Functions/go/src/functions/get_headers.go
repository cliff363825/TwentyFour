package functions

import (
	"log"
	"net/http"
)

func GetHeaders(url string) map[string][]string {
	client := &http.Client{
		CheckRedirect: func(req *http.Request, via []*http.Request) error {
			return http.ErrUseLastResponse
		},
	}
	resp, err := client.Get(url)
	if err != nil {
		log.Fatal(err)
	}
	return resp.Header
}
