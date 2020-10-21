package functions

import "net"

func Ip2long(ipAddress string) uint32 {
	ip := net.ParseIP(ipAddress)
	if ip == nil {
		return 0
	}
	var result uint32
	ipBytes := ip.To4()
	for _, v := range ipBytes {
		result = (result << 8) | uint32(v & 0xFF)
	}
	return result
}
