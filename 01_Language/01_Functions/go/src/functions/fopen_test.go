package functions

import (
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"testing"
)

func TestFopen(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_RDWR|os.O_CREATE, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	bytes, _ := ioutil.ReadAll(file)
	fmt.Println(string(bytes))
}

