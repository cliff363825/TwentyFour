package functions

import "path/filepath"

func Glob(pattern string) ([]string, error) {
	return filepath.Glob(pattern)
}
