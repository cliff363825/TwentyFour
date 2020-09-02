package functions

import (
	"log"
	"net"
)

func Getmxrr(hostname string) []string {
	mxes, err := net.LookupMX(hostname)
	if err != nil {
		log.Fatal(err)
	}
	res := make([]string, 0)
	for _, v := range mxes {
		res = append(res, v.Host)
	}
	return res
}
