package functions

import (
	"io/ioutil"
	"os"
)

func FilePutContents(filename string, data []byte, append bool, perm os.FileMode) error {
	if !append {
		return ioutil.WriteFile(filename, data, perm)
	}

	f, err := os.OpenFile(filename, os.O_WRONLY|os.O_CREATE|os.O_APPEND, perm)
	if err != nil {
		return err
	}
	_, err = f.Write(data)
	if err1 := f.Close(); err == nil {
		err = err1
	}
	return err
}
