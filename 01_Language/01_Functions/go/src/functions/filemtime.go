package functions

import (
	"os"
	"time"
)

func Filemtime(filename string) time.Time {
	if fileInfo, err := os.Stat(filename); err == nil {
		return fileInfo.ModTime()
	}
	return time.Unix(0, 0)
}
