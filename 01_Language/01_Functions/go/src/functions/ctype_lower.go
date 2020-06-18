package functions

func CtypeLower(s string) bool {
	if len(s) == 0 {
		return false
	}
	for i := 0; i < len(s); i++ {
		codePoint := int(s[i])
		if codePoint >= 97 && codePoint <= 122 {
			continue
		}
		return false
	}
	return true
}
