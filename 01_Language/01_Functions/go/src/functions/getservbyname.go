package functions

import "net"

func Getservbyname(service string, protocol string) (int, error) {
	return net.LookupPort(protocol, service)
}
