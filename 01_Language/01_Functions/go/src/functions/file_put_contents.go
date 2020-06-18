package functions

import (
	"io"
	"os"
)

func FilePutContents(filename string, data []byte, append bool, perm os.FileMode) error {
	//return ioutil.WriteFile(filename, data, perm)
	flag := os.O_WRONLY | os.O_CREATE
	if append {
		flag = flag | os.O_APPEND
	} else {
		flag = flag | os.O_TRUNC
	}
	f, err := os.OpenFile(filename, flag, perm)
	if err != nil {
		return err
	}
	n, err := f.Write(data)
	if err == nil && n < len(data) {
		err = io.ErrShortWrite
	}
	if err1 := f.Close(); err == nil {
		err = err1
	}
	return err
}
