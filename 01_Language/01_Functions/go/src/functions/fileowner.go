package functions

import (
	"os"
	"syscall"
)

func Fileowner(filename string) uint32 {
	if fileInfo, err := os.Stat(filename); err == nil {
		return fileInfo.Sys().(*syscall.Stat_t).Uid
	}
	return 0
}
