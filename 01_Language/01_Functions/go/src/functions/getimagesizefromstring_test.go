package functions

import (
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"testing"
)

func TestGetimagesizefromstring(t *testing.T) {
	file, err := os.Open("test.gif")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	bytes, err := ioutil.ReadAll(file)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(Getimagesizefromstring(bytes))
}
