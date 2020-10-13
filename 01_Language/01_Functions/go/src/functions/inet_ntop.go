package functions

import "net"

func InetNtop(inAddr []byte) string {
	return net.IP(inAddr).String()
}
