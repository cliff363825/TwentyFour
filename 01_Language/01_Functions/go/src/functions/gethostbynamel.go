package functions

import (
	"log"
	"net"
)

func Gethostbynamel(hostname string) []string {
	addrs, err := net.LookupHost(hostname)
	if err != nil {
		log.Println(err)
		return []string{}
	}

	return addrs
}
