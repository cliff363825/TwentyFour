package functions

import (
	"strings"
)

func Join(glue string, pieces []string) string {
	return strings.Join(pieces, glue)
}
