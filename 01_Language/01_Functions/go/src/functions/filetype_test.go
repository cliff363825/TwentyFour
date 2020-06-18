package functions

import (
	"fmt"
	"testing"
)

func TestFiletype(t *testing.T) {
	fmt.Println(Filetype("/dev/urandom"))
	fmt.Println(Filetype("/dev/"))
	fmt.Println(Filetype("/dev/disk0"))
	fmt.Println(Filetype("/dev/stdin"))
	fmt.Println(Filetype("/etc/passwd"))
	fmt.Println(Filetype("/var/run/syslog"))
}
