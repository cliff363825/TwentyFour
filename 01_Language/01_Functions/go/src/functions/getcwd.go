package functions

import (
	"log"
	"os"
)

func Getcwd() string {
	dir, err := os.Getwd()
	if err != nil {
		log.Fatal(err)
	}
	return dir
}
