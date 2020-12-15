package functions

import (
	"fmt"
	"testing"
)

func TestNatcasesort(t *testing.T) {
	var array []string = []string{"IMG0.png", "img12.png", "img10.png", "img2.png", "img1.png", "IMG3.png"}
	Natcasesort(array)
	fmt.Println(array)
}

