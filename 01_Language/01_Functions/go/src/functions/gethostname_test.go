package functions

import (
	"fmt"
	"testing"
)

func TestGethostname(t *testing.T) {
	fmt.Println(Gethostname())
}
