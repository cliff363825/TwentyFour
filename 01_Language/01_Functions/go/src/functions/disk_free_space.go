package functions

import "syscall"

func DiskFreeSpace(directory string) (uint64, error) {
	fs := syscall.Statfs_t{}
	err := syscall.Statfs(directory, &fs)
	if err != nil {
		return 0, err
	}
	return fs.Bavail * uint64(fs.Bsize), nil
}
