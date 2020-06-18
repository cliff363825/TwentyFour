package functions

import "os"

func Filesize(filename string) int64 {
	if fileInfo, err := os.Stat(filename); err == nil {
		return fileInfo.Size()
	}
	return 0
}
