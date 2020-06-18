package functions

import (
	"fmt"
	"log"
	"os"
	"testing"
	"time"
)

func TestFprintf(t *testing.T) {
	file, err := os.OpenFile("test.txt", os.O_WRONLY|os.O_TRUNC, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	now := time.Now()
	year, month, day := now.Year(), int(now.Month()), now.Day()
	fmt.Fprintf(file, "%04d-%02d-%02d", year, month, day)
}
