package functions

import (
	"fmt"
	"testing"
)

func TestInArray(t *testing.T) {
	os := []string{"Mac", "NT", "Irix", "Linux"}
	var osInterface = make([]interface{}, len(os))
	fmt.Println(osInterface)
	for _, v := range os {
		osInterface = append(osInterface, v)
	}
	fmt.Println(InArray("Irix", osInterface))
	fmt.Println(InArray("mac", osInterface))
}

