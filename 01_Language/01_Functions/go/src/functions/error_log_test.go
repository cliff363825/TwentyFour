package functions

import (
	"log"
	"testing"
)

func TestErrorLog(t *testing.T) {
	log.Fatalln("You messed up!")
}
