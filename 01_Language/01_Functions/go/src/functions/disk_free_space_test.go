package functions

import (
	"fmt"
	"testing"
)

func TestDiskFreeSpace(t *testing.T) {
	fmt.Println(DiskFreeSpace("/"))
}

func TestDiskTotalSpace(t *testing.T) {
	fmt.Println(DiskTotalSpace("/"))
}


