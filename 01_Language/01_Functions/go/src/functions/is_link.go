package functions

import (
	"os"
)

func IsLink(filename string) bool {
	lstat, err := os.Lstat(filename)
	if err != nil {
		return false
	}
	if lstat.Mode() & os.ModeSymlink != 0 {
		return true
	}
	return false
}
