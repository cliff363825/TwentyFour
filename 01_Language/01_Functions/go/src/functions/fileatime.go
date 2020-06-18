package functions

import (
	"os"
	"syscall"
	"time"
)

func Fileatime(filename string) time.Time {
	if fileInfo, err := os.Stat(filename); err == nil {
		return time.Unix(fileInfo.Sys().(*syscall.Stat_t).Atimespec.Unix())
	}
	return time.Unix(0, 0)
}
