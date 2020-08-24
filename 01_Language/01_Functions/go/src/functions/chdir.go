package functions

import (
	"log"
	"os"
)

func Chdir(path string) bool {
	err := os.Chdir(path)
	if err != nil {
		log.Fatal(err)
		return false
	}
	return true
}
