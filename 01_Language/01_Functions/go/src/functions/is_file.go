package functions

import "os"

func IsFile(filename string) bool {
	stat, err := os.Stat(filename)
	if err != nil {
		return false
	}
	return stat.Mode().IsRegular()
}
