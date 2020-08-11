package functions

import (
	"fmt"
	"testing"
)

func TestGetHeaders(t *testing.T) {
	headers := GetHeaders("http://www.onevgo.com")
	fmt.Println(headers)
}

