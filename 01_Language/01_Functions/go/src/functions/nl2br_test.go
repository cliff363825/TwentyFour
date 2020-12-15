package functions

import (
	"fmt"
	"testing"
)

func TestNl2br(t *testing.T) {
	fmt.Println(Nl2br("foo isn't\n bar"))
	fmt.Println(Nl2br("foo isn't\r bar"))
	fmt.Println(Nl2br("foo isn't\r\n bar"))
}
