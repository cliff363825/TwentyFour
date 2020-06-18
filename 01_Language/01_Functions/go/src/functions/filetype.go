package functions

import "os"

func Filetype(filename string) string {
	if fileInfo, err := os.Lstat(filename); err == nil {
		mode := fileInfo.Mode()
		if mode&os.ModeNamedPipe != 0 {
			return "fifo"
		} else if mode&os.ModeCharDevice != 0 {
			return "char"
		} else if mode&os.ModeDevice != 0 {
			return "block"
		} else if mode.IsDir() {
			return "dir"
		} else if mode&os.ModeSymlink != 0 {
			return "link"
		} else if mode&os.ModeSocket != 0 {
			return "socket"
		} else if mode.IsRegular() {
			return "file"
		}
	}
	return "unknown"
}
