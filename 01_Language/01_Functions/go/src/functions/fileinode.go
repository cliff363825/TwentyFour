package functions

import (
	"os"
	"syscall"
)

func Fileinode(filename string) uint64 {
	if fileInfo, err := os.Stat(filename); err == nil {
		return fileInfo.Sys().(*syscall.Stat_t).Ino
	}
	return 0
}
