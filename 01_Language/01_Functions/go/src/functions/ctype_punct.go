package functions

func CtypePunct(s string) bool {
	if len(s) == 0 {
		return false
	}
	for i := 0; i < len(s); i++ {
		codePoint := int(s[i])
		if (codePoint > 32 && codePoint <= 47) || (codePoint >= 58 && codePoint <= 64) ||
			(codePoint >= 91 && codePoint <= 96) || (codePoint >= 123 && codePoint <= 126) {
			continue
		}
		return false
	}
	return true
}
