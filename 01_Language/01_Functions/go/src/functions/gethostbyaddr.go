package functions

import (
	"log"
	"net"
)

func Gethostbyaddr(ip string) string {
	names, err := net.LookupAddr(ip)
	if err != nil {
		log.Println(err)
		return ip
	}
	return names[0]
}
