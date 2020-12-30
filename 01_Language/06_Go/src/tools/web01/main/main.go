package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
)

// single_curl("http://localhost:8080/log", json_encode($couponInfo));
func main() {
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Hello, %q", r.URL.Path)
	})
	http.HandleFunc("/log", func(w http.ResponseWriter, r *http.Request) {
		bytes, err := ioutil.ReadAll(r.Body)
		if err != nil {
			log.Println(r.RequestURI, err)
			return
		}
		log.Println(r.RequestURI, string(bytes))
	})

	log.Fatal(http.ListenAndServe(":8080", nil))
}
