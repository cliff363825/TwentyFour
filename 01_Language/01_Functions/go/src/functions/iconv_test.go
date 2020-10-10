package functions

import (
	"bytes"
	"fmt"
	"golang.org/x/text/encoding/charmap"
	"golang.org/x/text/transform"
	"io/ioutil"
	"testing"
)

func TestIconv(t *testing.T) {
	text := "This is the Euro symbol '€'."
	reader := transform.NewReader(bytes.NewReader([]byte(text)), charmap.ISO8859_1.NewEncoder())
	d, err := ioutil.ReadAll(reader)

	fmt.Println(string(d), err)
}

