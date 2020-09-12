package functions

import (
	"fmt"
	"testing"
)

func TestGetservbyname(t *testing.T) {
	var services = [...]string{"http", "ftp", "ssh", "telnet", "imap",
		"smtp", "nicname", "gopher", "finger", "pop3", "www"}
	for _, v := range services {
		p, _ := Getservbyname(v, "tcp")
		fmt.Println(v, p)
	}
}
