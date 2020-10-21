package functions

import (
	"fmt"
	"testing"
)

func TestIp2long(t *testing.T) {
	fmt.Println(Ip2long(Gethostbyname("www.onevgo.com")))
	fmt.Println(Ip2long("193.112.23.137"))
	fmt.Println(Ip2long("999.999.999.999"))
}
