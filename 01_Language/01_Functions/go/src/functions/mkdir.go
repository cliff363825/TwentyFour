package functions

import "os"

func Mkdir(pathname string, mode os.FileMode, recursive bool) bool {
	var err error
	if recursive {
		err = os.MkdirAll(pathname, mode)
	} else {
		err = os.Mkdir(pathname, mode)
	}
	return err == nil
}
