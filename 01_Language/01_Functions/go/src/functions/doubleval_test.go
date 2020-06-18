package functions

import (
	"fmt"
	"testing"
)

func TestDoubleval(t *testing.T) {
	fmt.Println(Doubleval("122.34343The"))
	fmt.Println(Doubleval("+122.34343.1"))
	fmt.Println(Doubleval("-122.34343.1"))
	fmt.Println(Doubleval("-.34343.1"))
	fmt.Println(Doubleval("The122.34343"))
}

