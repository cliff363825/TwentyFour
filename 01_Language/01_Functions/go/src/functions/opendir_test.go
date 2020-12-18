package functions

import (
	"fmt"
	"io/ioutil"
	"testing"
)

func TestOpendir(t *testing.T) {
	files, _ := ioutil.ReadDir("/Users/")
	for _, f := range files {
		fmt.Println(f.Name())
	}
}
