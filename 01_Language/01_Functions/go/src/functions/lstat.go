package functions

import "os"

func Lstat(filename string) os.FileInfo {
	if fileInfo, err := os.Lstat(filename); err == nil {
		return fileInfo
	}
	return nil
}
