package functions

import (
	"encoding/csv"
	"fmt"
	"os"
	"testing"
)

func TestFgetcsv(t *testing.T) {
	file, err := os.Open("test.csv")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()
	reader := csv.NewReader(file)
	reader.Comma = ','
	for {
		record, err := reader.Read()
		if err != nil {
			break
		}
		fmt.Println(record)
	}
}
