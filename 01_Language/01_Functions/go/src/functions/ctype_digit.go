package functions

func CtypeDigit(s string) bool {
	if len(s) == 0 {
		return false
	}
	for i := 0; i < len(s); i++ {
		codePoint := int(s[i])
		if codePoint >= 48 && codePoint <= 57 {
			continue
		}
		return false
	}
	return true
}
