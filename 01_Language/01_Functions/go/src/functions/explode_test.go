package functions

import (
	"fmt"
	"testing"
)

func TestExplode(t *testing.T) {
	fmt.Println(Explode(" ", "piece1 piece2 piece3 piece4 piece5 piece6"))
	fmt.Println(Explode(":", "foo:*:1023:1000::/home/foo:/bin/sh"))
	fmt.Println(ExplodeLimit("|", "one|two|three|four", 2))
	fmt.Println(ExplodeLimit("|", "one|two|three|four", -1))
	fmt.Println(ExplodeLimit("|", "one|two|three|four", 0))
}
