package functions

import (
	"fmt"
	"testing"
)

func TestMtRand(t *testing.T) {
	fmt.Println(MtRand())
	fmt.Println(MtRandRange(5, 15))
	fmt.Println(MtRandRange(1, 3))
}
