package functions

import "net"

func InetPton(address string) []byte {
	ip := net.ParseIP(address)
	return ip
}
