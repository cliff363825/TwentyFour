package functions

import (
	"fmt"
	"testing"
)

func TestArrayColumn(t *testing.T) {
	records := []map[string]interface{}{
		{
			"id":         2135,
			"first_name": "John",
			"last_name":  "Doe",
		},
		{
			"id":         3245,
			"first_name": "Sally",
			"last_name":  "Smith",
		},
		{
			"id":         5342,
			"first_name": "Jane",
			"last_name":  "Jones",
		},
		{
			"id":         5623,
			"first_name": "Peter",
			"last_name":  "Doe",
		},
	}
	fmt.Println(ArrayColumn(records, "first_name"))
	fmt.Println(ArrayColumnIndex(records, "first_name", "id"))
	column := ArrayColumnIndex(records, "first_name", "id")
	for k, v := range column {
		if k1, ok := k.(int); ok {
			if v1, ok := v.(string); ok {
				fmt.Println(k1, v1)
			}
		}
	}
}
