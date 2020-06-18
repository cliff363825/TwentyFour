package functions

import (
	"os"
	"syscall"
)

func Filegroup(filename string) uint32 {
	if fileInfo, err := os.Stat(filename); err == nil {
		return fileInfo.Sys().(*syscall.Stat_t).Gid
	}
	return 0
}
