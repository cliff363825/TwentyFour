package functions

import (
	"log"
	"os"
	"testing"
)

func TestFwrite(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_WRONLY|os.O_CREATE|os.O_APPEND, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	file.WriteString("Add this to the file\n")
}
