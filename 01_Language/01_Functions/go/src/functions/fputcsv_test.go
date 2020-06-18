package functions

import (
	"encoding/csv"
	"log"
	"os"
	"testing"
)

func TestFputcsv(t *testing.T) {
	file, err := os.OpenFile("test.csv", os.O_RDWR|os.O_CREATE|os.O_TRUNC, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	writer := csv.NewWriter(file)

	rows := [][]string{
		{"aaa", "bbb", "ccc", "dd,dd"},
		{"123", "456", "789"},
		{"\"aaa\"", "\"bbb\""},
	}
	writer.WriteAll(rows)
}
