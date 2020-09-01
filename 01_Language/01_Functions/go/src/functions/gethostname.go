package functions

import (
	"log"
	"os"
)

func Gethostname() string {
	name, err := os.Hostname()
	if err != nil {
		log.Println(err)
	}
	return name
}
