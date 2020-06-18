package functions

func CtypeCntrl(s string) bool {
	if len(s) == 0 {
		return false
	}
	for i := 0; i < len(s); i++ {
		codePoint := int(s[i])
		if codePoint >= 0 && codePoint <= 31 || codePoint == 127 {
			continue
		}
		return false
	}
	return true
}
