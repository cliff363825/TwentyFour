package functions

import (
	"fmt"
	"io/ioutil"
	"path/filepath"
	"testing"
)

func TestDir(t *testing.T) {
	fileInfos, _ := ioutil.ReadDir("./")
	for _, fileInfo := range fileInfos {
		filePath, _ := filepath.Abs(filepath.Join("./", fileInfo.Name()))
		fmt.Println(filePath)
	}
}
