package functions

func ctype_space(s string) bool {
	if len(s) == 0 {
		return false
	}
	for i := 0; i < len(s); i++ {
		codePoint := int(s[i])
		if codePoint == 32 || codePoint == 9 || codePoint == 11 ||
			codePoint == 10 || codePoint == 13 || codePoint == 12 {
			continue
		}
		return false
	}
	return true
}
