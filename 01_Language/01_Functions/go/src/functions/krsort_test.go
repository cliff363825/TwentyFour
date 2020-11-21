package functions

import (
	"fmt"
	"sort"
	"testing"
)

func TestKrsort(t *testing.T) {
	var fruits = map[string]string{
		"d": "lemon",
		"a": "orange",
		"b": "banana",
		"c": "apple",
	}
	var keys = make([]string, len(fruits))
	var i = 0
	for k, v := range fruits {
		fmt.Println(k, v)

		keys[i] = k
		i++
	}
	fmt.Println(keys)
	sort.Sort(sort.Reverse(sort.StringSlice(keys)))
	fmt.Println(keys)

	for _, v := range keys {
		fmt.Println(v, fruits[v])
	}
}

