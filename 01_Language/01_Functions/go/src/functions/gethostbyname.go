package functions

import (
	"log"
	"net"
)

func Gethostbyname(hostname string) string {
	addrs, err := net.LookupHost(hostname)
	if err != nil {
		log.Println(err)
		return hostname
	}
	return addrs[0]
}
