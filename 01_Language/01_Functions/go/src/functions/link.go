package functions

import "os"

func Link(target string, link string) bool {
	if err := os.Link(target, link); err == nil {
		return true
	}
	return false
}
