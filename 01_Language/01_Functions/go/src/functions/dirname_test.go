package functions

import (
	"fmt"
	"path/filepath"
	"testing"
)

func TestDirname(t *testing.T) {
	fmt.Println(filepath.Dir("/etc/passwd"))
	fmt.Println(filepath.Dir("/etc/"))
	fmt.Println(filepath.Dir("/"))
	fmt.Println(filepath.Dir("//"))
	fmt.Println(filepath.Dir("."))
	fmt.Println(filepath.Dir("C:\\"))
	fmt.Println(filepath.Dir("/usr/local/lib"))
}

