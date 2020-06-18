package functions

import (
	"fmt"
	"log"
	"os"
	"testing"
)

func TestFstat(t *testing.T) {
	info, err := os.Stat("test.txt")
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("%+v\n", info)
}
